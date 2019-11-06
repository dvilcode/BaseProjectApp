package $packageName.persistence;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import $packageName.entities.ForaneaDTO;
import $packageName.exception.JaverianaException;
import $packageName.interfaces.persistence.IGenericDAO;
import $packageName.interfaces.services.IJaverianaExceptionService;
import $packageName.utils.Parameters;

@Repository
public class GenericDAO extends NamedParameterJdbcDaoSupport implements IGenericDAO {
	private static final Logger bitácora = Logger.getRootLogger();

	@Autowired
	IJaverianaExceptionService javerianaExceptionService;

	private OracleJdbcTemplate oracleTemplate;

	@Autowired
	@Qualifier("principalDataSource")
	public void dataSource(BasicDataSource dataSource) {
		this.setDataSource(dataSource);
		oracleTemplate = new OracleJdbcTemplate(dataSource);
	}

	public void create(Object entity, String sql) throws JaverianaException {
		try {
			SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
			this.getNamedParameterJdbcTemplate().update(sql, namedParameters);
		} catch (Exception e) {
			bitácora.error("GenericDAO.create. Causa: " + e.getMessage() + ". Query: " + sql);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_GENERIC_DAO, null);
		}
	}

	public BigDecimal create(Object entity, String sql, String[] columnNames) throws JaverianaException {
		try {
			SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
			KeyHolder keyHolder = new GeneratedKeyHolder();

			this.oracleTemplate.update(sql, namedParameters, keyHolder, columnNames);
			return keyHolder.getKey() != null ? new BigDecimal(keyHolder.getKey().longValue()) : null;
		} catch (Exception e) {
			bitácora.error("GenericDAO.create. Causa: " + e.getMessage() + ". Query: " + sql);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_GENERIC_DAO, null);
		}
	}

	public void update(Object entity, String sql) throws JaverianaException {
		try {
			SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
			this.getNamedParameterJdbcTemplate().update(sql, namedParameters);
		} catch (Exception e) {
			bitácora.error("GenericDAO.update. Causa:" + e.getMessage() + ". Query: " + sql);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_GENERIC_DAO, null);
		}
	}

	public void delete(Object entity, String sql) throws JaverianaException {
		try {
			SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
			this.getNamedParameterJdbcTemplate().update(sql, namedParameters);
		} catch (Exception e) {
			bitácora.error("GenericDAO.delete. Causa:" + e.getMessage() + ". Query: " + sql);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_GENERIC_DAO, null);
		}
	}

	public List findByCriteria(Object entity, String sql, String order, boolean isDesc) throws JaverianaException {
		List results = null;
		try {
			SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
			if (order != null && !order.equals("")) {
				sql += " order by " + order + (isDesc ? " desc " : " asc ");
			}
			results = this.getNamedParameterJdbcTemplate().query(sql, namedParameters, new BeanPropertyRowMapper(entity.getClass()));
		} catch (Exception e) {
			bitácora.error("GenericDAO.findByCriteria. Causa: " + e.getMessage() + ". Query " + sql, e);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_GENERIC_DAO, null);
		}
		return results;
	}

	public Object findByPK(Object entity, String sql) throws JaverianaException {
		List results = null;
		try {
			SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);

			results = this.getNamedParameterJdbcTemplate().query(sql, namedParameters, new BeanPropertyRowMapper(entity.getClass()));
		} catch (Exception e) {
			bitácora.error("GenericDAO.findByCriteria. Causa: " + e.getMessage() + ". Query " + sql);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_GENERIC_DAO, null);
		}
		if (results != null && !results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}

	public void registerLog(String usuario, String emplid, String accion, String texto) throws JaverianaException {
		Map<String, Object> parameters = null;
		String sql = null;
		try {
			sql = "INSERT INTO PUJ_SYSADM.LOG_USUAPP (FECHA,USUARIO,EMPLID,APLICACION,ACCION,TEXTO) " + "VALUES (:fecha, :usuario, :emplid, :aplicacion, :accion, :texto)";

			parameters = new HashMap<>();
			parameters.put("fecha", new Timestamp(System.currentTimeMillis()));
			parameters.put("usuario", usuario);
			parameters.put("emplid", emplid);
			parameters.put("aplicacion", Parameters.NOMBRE_APLICACION);
			parameters.put("accion", accion);
			if (texto == null) {
				parameters.put("texto", "");
			} else {
				parameters.put("texto", texto);
			}

			this.getNamedParameterJdbcTemplate().update(sql, parameters);

		} catch (Exception e) {
			bitácora.error("GenericDAO.registerLog. Causa: " + e.getMessage() + ". Query: " + sql);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_GENERIC_DAO, null);
		}
	}

	public List findByCriteriaForeign(Object entity, String tabla, String select, String where, List<ForaneaDTO> foraneasList, HashMap<String, String> aliasForaneas)
			throws JaverianaException {
		List results = null;
		String sql = null;
		StringBuilder selectStatement = new StringBuilder("select ");
		StringBuilder fromStatement = new StringBuilder("from " + tabla);
		String[] arrayCampos;
		String aliasCampoForeing = null;
		try {
			// se agregua el nombre de la tabla padre a sus respectivos campos
			arrayCampos = select.split(",");
			for (int i = 0; i < arrayCampos.length; i++) {
				selectStatement.append(tabla + "." + arrayCampos[i]);
				selectStatement.append(i != (arrayCampos.length - 1) ? "," : "");// se
																					// determina
																					// si
																					// es
																					// el
																					// ultimo
																					// campo
																					// para
																					// ponerle
																					// una
																					// coma
			}
			// Ajustamos el select con los campos que se requieren de las
			// foraneas
			for (int j = 0; j < foraneasList.size(); j++) {
				arrayCampos = foraneasList.get(j).getCampos().split(",");
				selectStatement.append(",");
				for (int i = 0; i < arrayCampos.length; i++) {
					// Si la longitud del campo es mayor a 27 no se le asigna el
					// prefijo
					if (arrayCampos[i].length() <= 27) {
						aliasCampoForeing = aliasForaneas.get(foraneasList.get(j).getTabla()) + "_" + arrayCampos[i];
					} else {
						aliasCampoForeing = arrayCampos[i];
					}

					selectStatement.append(foraneasList.get(j).getTabla() + "." + arrayCampos[i] + " " + aliasCampoForeing);// se
																															// pone
																															// en
																															// el
																															// select
																															// los
																															// campos
																															// de
																															// la
																															// foranea
																															// y
																															// su
																															// respectivo
																															// aleas
					selectStatement.append(i != (arrayCampos.length - 1) ? "," : "");// se
																						// determina
																						// si
																						// es
																						// el
																						// ultimo
																						// campo
																						// para
																						// ponerle
																						// una
																						// coma
				}
				// creamos el join
				fromStatement.append(" JOIN " + foraneasList.get(j).getTabla() + " ON " + tabla + "." + foraneasList.get(j).getCampoForanea() + "=" + foraneasList.get(j).getTabla()
						+ "." + foraneasList.get(j).getIdForanea());
			}

			SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
			sql = selectStatement.toString() + " " + fromStatement.toString() + " " + (where == null ? "1=1" : where);
			results = this.getNamedParameterJdbcTemplate().query(sql, namedParameters, new BeanPropertyRowMapper(entity.getClass()));
		} catch (Exception e) {
			bitácora.error("GenericDAO.findByCriteriaForeign. Causa: " + e.getMessage() + ". Query " + sql);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_GENERIC_DAO, null);
		}
		return results;
	}

	public List findByQueryForeign(Object entity, String sql) throws JaverianaException {
		List results = null;
		try {
			SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
			results = this.getNamedParameterJdbcTemplate().query(sql, namedParameters, new BeanPropertyRowMapper(entity.getClass()));
		} catch (Exception e) {
			bitácora.error("GenericDAO.findByQueryForeign. Causa: " + e.getMessage() + ". Query " + sql);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_GENERIC_DAO, null);
		}
		return results;
	}

	public List<Map<String, Object>> findByCriteria(String sql) throws JaverianaException {
		List<Map<String, Object>> results = null;
		try {
			results = this.getNamedParameterJdbcTemplate().queryForList(sql, new HashMap<String, String>());
		} catch (Exception e) {
			bitácora.error("GenericDAO.findByCriteria. Causa: " + e.getMessage() + ". Query " + sql);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_GENERIC_DAO, null);
		}
		return results;
	}

	public Map<String, Object> findByPKGeneral(String sql, Map<String, ?> parametros) throws JaverianaException {
		List<Map<String, Object>> results = null;
		try {
			results = this.getNamedParameterJdbcTemplate().queryForList(sql, parametros);
		} catch (Exception e) {
			bitácora.error("GenericDAO.findByPKGeneral. Causa: " + e.getMessage() + ". Query " + sql);
			throw javerianaExceptionService.throwException("base01", null);
		}
		if (results != null && results.size() > 0) {
			return results.get(0);
		}
		return null;
	}

}
