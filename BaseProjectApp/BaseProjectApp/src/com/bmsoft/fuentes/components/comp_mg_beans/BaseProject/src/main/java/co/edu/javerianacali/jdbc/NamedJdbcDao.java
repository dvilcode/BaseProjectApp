package $packageName.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 * @author jcrada
 *
 */
public class NamedJdbcDao extends NamedParameterJdbcDaoSupport {
	SimpleJdbcCall simpleJdbcCall;

	public SimpleJdbcCall getSimpleJdbcCall() {
		if (simpleJdbcCall != null)
			return simpleJdbcCall;
		return new SimpleJdbcCall(this.getJdbcTemplate());
	}

	@Autowired
	@Qualifier("principalDataSource")
	public void dataSource(BasicDataSource dataSource) {
		this.setDataSource(dataSource);
	}
}
