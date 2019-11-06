package $packageName.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import $packageName.exception.JaverianaException;
import $packageName.interfaces.persistence.IGenericDAO;
import $packageName.interfaces.services.IJaverianaExceptionService;
import $packageName.utils.Parameters;
import $packageName.interfaces.services.ITipoParametroService;
import java.io.Serializable;
import org.apache.log4j.Logger;
import java.util.List;
import $packageName.entities.TipoParametroDTO;
import $packageName.entities.ForaneaDTO;
import java.util.ArrayList;

@Service("TipoParametroService")
public class TipoParametroService implements ITipoParametroService {
	private static final long serialVersionUID = 1L;
	private static final Logger bitacora = Logger.getRootLogger();

	@Autowired
	private IGenericDAO genericDao;
	@Autowired
	private IJaverianaExceptionService javerianaExceptionService;

	@Override
	@Transactional(rollbackFor = JaverianaException.class)
	public void create(TipoParametroDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("INSERT INTO TIPO_PARAMETRO (" + " ID_TIPO_PARAMETRO," + " NOMBRE," + " USUARIO_CREA," + " ACTIVO," + " FECHA_CREACION" + " )" + " VALUES ("
					+ " :idTipoParametro,"

					+ " :nombre,"

					+ " :usuarioCrea,"

					+ " :activo,"

					+ " :fechaCreacion"

					+ " )");

			genericDao.create(entity, sql.toString());
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServiceTipoParametroService.create. Causa: " + e.getMessage() + ". Query: " + sql.toString());
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	@Transactional(rollbackFor = JaverianaException.class)
	public void update(TipoParametroDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("UPDATE TIPO_PARAMETRO SET " + " NOMBRE=:nombre," + " USUARIO_CREA=:usuarioCrea," + " ACTIVO=:activo," + " FECHA_CREACION=:fechaCreacion" + " WHERE "
					+ "ID_TIPO_PARAMETRO=:idTipoParametro");

			genericDao.update(entity, sql.toString());
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServiceTipoParametroService.update. Causa: " + e.getMessage() + ". Query: " + sql.toString());
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	@Transactional(rollbackFor = JaverianaException.class)
	public void delete(TipoParametroDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("DELETE FROM TIPO_PARAMETRO " + " WHERE " + "ID_TIPO_PARAMETRO=:idTipoParametro");

			genericDao.delete(entity, sql.toString());
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServiceTipoParametroService.delete. Causa: " + e.getMessage() + ". Query: " + sql.toString());
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	public List<TipoParametroDTO> findByCriteria(TipoParametroDTO entity, String orderField, boolean isDesc) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("select" + " ID_TIPO_PARAMETRO," + " NOMBRE," + " USUARIO_CREA," + " ACTIVO," + " FECHA_CREACION" + " FROM TIPO_PARAMETRO " + " WHERE 1=1 ");
			if (entity.getIdTipoParametro() != null)
				sql.append(" AND ID_TIPO_PARAMETRO=:idTipoParametro");
			if (entity.getNombre() != null && !entity.getNombre().equals(""))
				sql.append(" AND NOMBRE=:nombre");
			if (entity.getUsuarioCrea() != null && !entity.getUsuarioCrea().equals(""))
				sql.append(" AND USUARIO_CREA=:usuarioCrea");
			if (entity.getActivo() != null)
				sql.append(" AND ACTIVO=:activo");
			if (entity.getFechaCreacion() != null)
				sql.append(" AND FECHA_CREACION=:fechaCreacion");

			return genericDao.findByCriteria(entity, sql.toString(), orderField, isDesc);
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServiceTipoParametroService.findByCriteria. Causa: " + e.getMessage() + ". Query: " + sql.toString());
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	public TipoParametroDTO findByPK(TipoParametroDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("select" + " ID_TIPO_PARAMETRO," + " NOMBRE," + " USUARIO_CREA," + " ACTIVO," + " FECHA_CREACION" + " FROM TIPO_PARAMETRO "

					+ " WHERE " + "ID_TIPO_PARAMETRO=:idTipoParametro");

			return (TipoParametroDTO) genericDao.findByPK(entity, sql.toString());
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServiceTipoParametroService.findByPK. Causa: " + e.getMessage() + ". Query: " + sql.toString());
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	public List<TipoParametroDTO> findByCriteriaForeign(TipoParametroDTO entity) throws JaverianaException {
		StringBuilder select = new StringBuilder("");
		StringBuilder where = new StringBuilder("");
		List<ForaneaDTO> listForaneaDTO = new ArrayList<>();
		ForaneaDTO objForaneaDTO = null;
		try {
			select.append("" + " ID_TIPO_PARAMETRO," + " NOMBRE," + " USUARIO_CREA," + " ACTIVO," + " FECHA_CREACION");

			where.append(" WHERE 1=1 ");

			if (entity.getIdTipoParametro() != null)
				where.append(" AND TIPO_PARAMETRO.ID_TIPO_PARAMETRO=:idTipoParametro");
			if (entity.getNombre() != null && !entity.getNombre().equals(""))
				where.append(" AND TIPO_PARAMETRO.NOMBRE=:nombre");
			if (entity.getUsuarioCrea() != null && !entity.getUsuarioCrea().equals(""))
				where.append(" AND TIPO_PARAMETRO.USUARIO_CREA=:usuarioCrea");
			if (entity.getActivo() != null)
				where.append(" AND TIPO_PARAMETRO.ACTIVO=:activo");
			if (entity.getFechaCreacion() != null)
				where.append(" AND TIPO_PARAMETRO.FECHA_CREACION=:fechaCreacion");

			return genericDao.findByCriteriaForeign(entity, "TIPO_PARAMETRO", select.toString(), where.toString(), listForaneaDTO, entity.getAliasForaneas());
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServiceTipoParametroService.findByCriteriaForeign. Causa: " + e.getMessage() + ". Query: " + select.toString());
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}
}
