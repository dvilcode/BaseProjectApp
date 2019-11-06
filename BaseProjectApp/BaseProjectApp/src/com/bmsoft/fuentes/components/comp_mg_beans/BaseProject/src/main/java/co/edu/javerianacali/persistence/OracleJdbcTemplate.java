package $packageName.persistence;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.CallableStatementCreatorFactory;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.jdbc.core.namedparam.ParsedSql;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;

public class OracleJdbcTemplate extends NamedParameterJdbcTemplate {
	public OracleJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}

	private CallableStatementCreator getCallableStatementCreator(String sql, SqlParameterSource paramSource, String[] outParamNames) {
		ParsedSql parsedSql = this.getParsedSql(sql);
		String sqlToUse = NamedParameterUtils.substituteNamedParameters(parsedSql, paramSource);
		List<SqlParameter> declaredParameters = NamedParameterUtils.buildSqlParameterList(parsedSql, paramSource);

		Map<String, Object> params = new HashMap<>();
		for (SqlParameter param : declaredParameters) {
			params.put(param.getName(), paramSource.getValue(param.getName()));
		}

		for (String outParam : outParamNames) {
			declaredParameters.add(new SqlOutParameter(outParam, Types.NUMERIC));
		}

		CallableStatementCreatorFactory cscf = new CallableStatementCreatorFactory(sqlToUse, declaredParameters);
		return cscf.newCallableStatementCreator(params);
	}

	private String prepareSql(String sql, String[] keyColumnNames) {
		if (keyColumnNames.length == 0) {
			return sql;
		}

		Iterator<String> i$ = Arrays.asList(keyColumnNames).iterator();
		StringBuilder a = new StringBuilder();
		StringBuilder b = new StringBuilder();

		while (i$.hasNext()) {
			String keyColumn = i$.next();
			a.append(keyColumn);
			b.append("?");
			if (i$.hasNext()) {
				a.append(", ");
				b.append(", ");
			}
		}
		return "BEGIN " + sql + " RETURNING " + a.toString() + " INTO " + b.toString() + "; END;";
	}

	@Override
	public int update(String sql, SqlParameterSource paramSource, final KeyHolder generatedKeyHolder, final String[] keyColumnNames) {
		String preparedSql = this.prepareSql(sql, keyColumnNames);
		return (int) this.getJdbcOperations().execute(getCallableStatementCreator(preparedSql, paramSource, keyColumnNames), new CallableStatementCallback() {
			public Integer doInCallableStatement(CallableStatement callableStatement) throws SQLException {
				int rows = callableStatement.executeUpdate();
				List<Map<String, Object>> generatedKeys = generatedKeyHolder.getKeyList();
				generatedKeys.clear();
				int numParams = callableStatement.getParameterMetaData().getParameterCount();
				for (int n = numParams - keyColumnNames.length, i = n; i < numParams; i++) {
					try {
						Object key = callableStatement.getObject(i + 1);
						generatedKeys.add(Collections.singletonMap(keyColumnNames[i - n], key));
					} catch (SQLException e) {
						// log the exception
						throw e;
					}
				}
				return rows;
			}

		});
	}
}
