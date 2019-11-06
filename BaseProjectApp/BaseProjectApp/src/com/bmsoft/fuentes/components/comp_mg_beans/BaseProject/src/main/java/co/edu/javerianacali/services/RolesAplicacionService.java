package $packageName.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import $packageName.exception.JaverianaException;
import $packageName.interfaces.persistence.IGenericDAO;
import $packageName.interfaces.services.IJaverianaExceptionService;
import $packageName.utils.Parameters;
import $packageName.interfaces.services.IRolesAplicacionService;
import java.io.Serializable;
import org.apache.log4j.Logger;
import java.util.List;
import $packageName.entities.RolesAplicacionDTO;
import $packageName.entities.ForaneaDTO;
import java.util.ArrayList;

@Service("RolesAplicacionService")
public class RolesAplicacionService implements IRolesAplicacionService {
	private static final long serialVersionUID = 1L;
	private static final Logger bitacora = Logger.getRootLogger();

	@Autowired
	private IGenericDAO genericDao;
	@Autowired
	private IJaverianaExceptionService javerianaExceptionService;

	@Override
	@Transactional(rollbackFor = JaverianaException.class)
	public void create(RolesAplicacionDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {

			validateName(entity, true);

			sql.append("INSERT INTO NUEVO_PARQUEADERO.ROLES_APLICACION (" + " ID_ROL," + " NOMBRE," + " ROLES_PEOPLE," + " USUARIO_CREA,"
					+ " ACTIVO," + " FECHA_CREACION" + " )" + " VALUES (" + " S_ROLES_APLICACION.NEXTVAL ,"
					+ " :nombre,"

					+ " :rolesPeople,"

					+ " :usuarioCrea,"

					+ " :activo,"

					+ " :fechaCreacion"

					+ " )");

			genericDao.create(entity, sql.toString());
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error(
					"ServiceRolesAplicacionService.create. Causa: " + e.getMessage() + ". Query: " + sql.toString(), e);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	@Transactional(rollbackFor = JaverianaException.class)
	public void update(RolesAplicacionDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {

			validateName(entity, false);

			sql.append("UPDATE NUEVO_PARQUEADERO.ROLES_APLICACION SET " + " NOMBRE=:nombre," + " ROLES_PEOPLE=:rolesPeople,"
					+ " USUARIO_CREA=:usuarioCrea," + " ACTIVO=:activo," + " FECHA_CREACION=:fechaCreacion" + " WHERE "
					+ "ID_ROL=:idRol");

			genericDao.update(entity, sql.toString());
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error(
					"ServiceRolesAplicacionService.update. Causa: " + e.getMessage() + ". Query: " + sql.toString(), e);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	@Transactional(rollbackFor = JaverianaException.class)
	public void delete(RolesAplicacionDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("DELETE FROM NUEVO_PARQUEADERO.ROLES_APLICACION " + " WHERE " + "ID_ROL=:idRol");

			genericDao.delete(entity, sql.toString());
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error(
					"ServiceRolesAplicacionService.delete. Causa: " + e.getMessage() + ". Query: " + sql.toString(), e);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	public List<RolesAplicacionDTO> findByCriteria(RolesAplicacionDTO entity, String orderField, boolean isDesc)
			throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("select" + " ID_ROL," + " NOMBRE," + " ROLES_PEOPLE," + " USUARIO_CREA," + " ACTIVO,"
					+ " FECHA_CREACION" + " FROM NUEVO_PARQUEADERO.ROLES_APLICACION " + " WHERE 1=1 ");
			if (entity.getIdRol() != null && entity.getIdRol().intValue() != 0)
				sql.append(" AND ID_ROL=:idRol");
			if (entity.getNombre() != null && !entity.getNombre().equals(""))
				sql.append(" AND upper(NOMBRE) like  '%'||upper('%" + entity.getNombre() + "%')||'%'");
			if (entity.getRolesPeople() != null && !entity.getRolesPeople().equals(""))
				sql.append(" AND upper(ROLES_PEOPLE)=UPPER('" + entity.getRolesPeople() + "')");
			if (entity.getUsuarioCrea() != null && !entity.getUsuarioCrea().equals(""))
				sql.append(" AND upper(USUARIO_CREA) like  '%'||upper('%" + entity.getUsuarioCrea() + "%')||'%'");
			if (entity.getActivo() != null)
				sql.append(" AND ACTIVO=:activo");
			if (entity.getFechaCreacion() != null)
				sql.append(" AND FECHA_CREACION=:fechaCreacion");

			return genericDao.findByCriteria(entity, sql.toString(), orderField, isDesc);
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServiceRolesAplicacionService.findByCriteria. Causa: " + e.getMessage() + ". Query: "
					+ sql.toString(), e);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	public RolesAplicacionDTO findByPK(RolesAplicacionDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("select" + " ID_ROL," + " NOMBRE," + " ROLES_PEOPLE," + " USUARIO_CREA," + " ACTIVO,"
					+ " FECHA_CREACION" + " FROM NUEVO_PARQUEADERO.ROLES_APLICACION "

					+ " WHERE " + "ID_ROL=:idRol");

			return (RolesAplicacionDTO) genericDao.findByPK(entity, sql.toString());
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error(
					"ServiceRolesAplicacionService.findByPK. Causa: " + e.getMessage() + ". Query: " + sql.toString(),
					e);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	public List<RolesAplicacionDTO> findByCriteriaForeign(RolesAplicacionDTO entity) throws JaverianaException {
		StringBuilder select = new StringBuilder("");
		StringBuilder where = new StringBuilder("");
		List<ForaneaDTO> listForaneaDTO = new ArrayList<>();
		ForaneaDTO objForaneaDTO = null;
		try {
			select.append("" + " ID_ROL," + " NOMBRE," + " ROLES_PEOPLE," + " USUARIO_CREA," + " ACTIVO,"
					+ " FECHA_CREACION");

			where.append(" WHERE 1=1 ");

			if (entity.getIdRol() != null)
				where.append(" AND ROLES_APLICACION.ID_ROL=:idRol");

			if (entity.getNombre() != null && !entity.getNombre().equals(""))
				where.append(
						" AND upper(ROLES_APLICACION.NOMBRE) like  '%'||upper('%" + entity.getNombre() + "%')||'%'");

			if (entity.getRolesPeople() != null && !entity.getRolesPeople().equals(""))
				where.append(" AND upper(ROLES_APLICACION.ROLES_PEOPLE) like  '%'||upper('%" + entity.getRolesPeople()
						+ "%')||'%'");

			if (entity.getUsuarioCrea() != null && !entity.getUsuarioCrea().equals(""))
				where.append(" AND upper(ROLES_APLICACION.USUARIO_CREA) like  '%'||upper('%" + entity.getUsuarioCrea()
						+ "%')||'%'");

			if (entity.getActivo() != null)
				where.append(" AND ROLES_APLICACION.ACTIVO=:activo");

			if (entity.getFechaCreacion() != null)
				where.append(" AND ROLES_APLICACION.FECHA_CREACION=:fechaCreacion");

			return genericDao.findByCriteriaForeign(entity, "ROLES_APLICACION", select.toString(), where.toString(),
					listForaneaDTO, entity.getAliasForaneas());
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServiceRolesAplicacionService.findByCriteriaForeign. Causa: " + e.getMessage() + ". Query: "
					+ select.toString(), e);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	private void validateName(RolesAplicacionDTO entity, boolean creating) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("select 1 FROM NUEVO_PARQUEADERO.ROLES_APLICACION 	where nombre=:nombre");
			sql.append(creating ? "" : " and ID_ROL!=:idRol ");

			List<RolesAplicacionDTO> list = genericDao.findByCriteria(entity, sql.toString(), null, false);
			if (list != null && list.size() > 0)
				throw javerianaExceptionService.throwException(Parameters.EXCEPCION_NAME, null);

		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

}
