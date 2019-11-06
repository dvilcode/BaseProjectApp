package $packageName.security;

import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import $packageName.utils.StringUtil;

/**
 * @author jcrada
 * 
 */
@Repository
public class PeopleSoftDaoLocal extends NamedParameterJdbcDaoSupport {

	private static final Logger logger = Logger.getRootLogger();

	@Autowired
	@Qualifier("PSDataSource")
	public void dataSource(BasicDataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public String findEmplId(String userName, int secuencia) throws Exception {
		String consulta = "SELECT emplid emplid " + "from sysadm.psoprdefn " + "WHERE  puj_sysadm.Encryptdecrypt(:usuario, 'G', :sec) = oprid ";
		SqlParameterSource namedParameters = new MapSqlParameterSource("usuario", userName.toUpperCase()).addValue("sec", secuencia);

		logger.error("Usuario sec " + userName + "==" + secuencia + "==Query==" + consulta);

		try {
			String emplid = super.getNamedParameterJdbcTemplate().queryForObject(consulta, namedParameters, String.class);
			if (StringUtil.isEmptyOrNull(emplid)) {
				logger.error("No encontro emplid");
				return emplid;
			}

			return emplid;
		}

		catch (EmptyResultDataAccessException e) {
			logger.error("PeopleSoftDaoLocal.findEmplId, causa " + e.getMessage());
			return "";
		}
	}

	public String read(String usuario, int sec) {
		String query = "select puj_sysadm.EncryptDecrypt(:usuario,'G',:sec) from dual";
		SqlParameterSource namedParameters = new MapSqlParameterSource("usuario", usuario.toUpperCase()).addValue("sec", sec);

		logger.error("Usuario sec " + usuario + "==" + sec + "==Query==" + query);

		try {
			String usuarioPS = super.getNamedParameterJdbcTemplate().queryForObject(query, namedParameters, String.class);
			if (StringUtil.isEmptyOrNull(usuarioPS)) {
				logger.error("No encontro");
				return usuarioPS;
			}

			else {
				logger.error("Borro acc_portal" + usuarioPS);
				String deleteQuery = "delete puj_sysadm.ps_puj_acc_portal where consecutivo =:sec and oprid_encrypt=:usuario";
				super.getNamedParameterJdbcTemplate().update(deleteQuery, namedParameters);
				return usuarioPS;
			}

		}

		catch (EmptyResultDataAccessException e) {
			logger.error("read psdatasource " + e.toString());
			return "";
		}
	}

	public List<String> Roles(String usuario) {
		String query = "select rolename from sysadm.PSROLEUSER where UPPER(roleuser) = UPPER(:usuario)";
		SqlParameterSource namedParameters = new MapSqlParameterSource("usuario", usuario.toUpperCase());

		try {
			List<String> roles = super.getNamedParameterJdbcTemplate().queryForList(query, namedParameters, String.class);
			return roles;

		}

		catch (EmptyResultDataAccessException e) {
			logger.error("read psdatasource " + e.toString());
			return null;
		}
	}
}
