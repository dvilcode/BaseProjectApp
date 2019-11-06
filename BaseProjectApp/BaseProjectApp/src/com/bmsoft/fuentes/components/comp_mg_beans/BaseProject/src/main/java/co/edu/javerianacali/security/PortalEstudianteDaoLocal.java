package $packageName.security;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author jcrada
 * 
 */
@Repository
public class PortalEstudianteDaoLocal extends NamedParameterJdbcDaoSupport {

	private static final Logger logger = Logger.getRootLogger();

	@Autowired
	@Qualifier("principalDataSource")
	public void dataSource(BasicDataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public PortalEstudiante read(String Consecutivo) {
		String query = "select consecutivo, activo, fechasys, emplid, usuario from portal.permisos where Consecutivo = :Consecutivo";
		SqlParameterSource namedParameters = new MapSqlParameterSource("Consecutivo", Consecutivo);
		try {
			return super.getNamedParameterJdbcTemplate().queryForObject(query, namedParameters, new ParameterizedRowMapper<PortalEstudiante>() {
				public PortalEstudiante mapRow(ResultSet rs, int rowNum) throws SQLException {
					PortalEstudiante portal = new PortalEstudiante();
					portal.setConsecutivo(rs.getLong(("consecutivo")));
					portal.setActivo(rs.getString(("activo")));
					portal.setFechaSys(rs.getDate(("fechasys")));
					portal.setEmplid(rs.getString(("emplid")));
					portal.setUsuario((rs.getString(("usuario"))));
					return portal;
				}
			});
		} catch (EmptyResultDataAccessException e) {
			logger.error("read PortalEstudiante " + e.toString());
			return null;
		} catch (UncategorizedSQLException e) {
			logger.error("read PortalEstudiante " + e.toString());
			return null;
		}
	}

	public void updateTicket(String Consecutivo) {
		String query = "update portal.permisos set activo='No' where emplid=:Consecutivo";
		SqlParameterSource namedParameters = new MapSqlParameterSource("Consecutivo", Consecutivo);
		super.getNamedParameterJdbcTemplate().update(query, namedParameters);
	}

}
