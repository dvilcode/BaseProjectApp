package $packageName.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import $packageName.entities.ForaneaDTO;
import $packageName.entities.ParametroDTO;
import $packageName.exception.JaverianaException;
import $packageName.interfaces.persistence.IGenericDAO;
import $packageName.interfaces.services.IJaverianaExceptionService;
import $packageName.interfaces.services.IParametroService;
import $packageName.utils.Parameters;
import $packageName.interfaces.services.IParametroService;

import java.io.Serializable;

import org.apache.log4j.Logger;

import java.util.List;

import $packageName.entities.ParametroDTO;
import $packageName.entities.ForaneaDTO;

import java.util.ArrayList;

@Service("ParametroService")
public class ParametroService implements IParametroService {
	private static final long serialVersionUID = 1L;
	private static final Logger bitacora = Logger.getRootLogger();

	@Autowired
	private IGenericDAO genericDao;
	@Autowired
	private IJaverianaExceptionService javerianaExceptionService;

	private static String SQL_WHERE_EXTRA = ":whereParam";

	@Override
	@Transactional(rollbackFor = JaverianaException.class)
	public void create(ParametroDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");

		ParametroDTO objDTO = new ParametroDTO();
		objDTO.setNombre(entity.getNombre());
		List<ParametroDTO> listDTO = findByCriteria(objDTO);

		try {
			sql.append("INSERT INTO SCYD.PARAMETRO (" + " ID_PARAMETRO," + " ID_TIPO_PARAMETRO," + " NOMBRE," + " DESCRIPCION," + " VALOR," + " USUARIO_CREA,"
					+ " FECHA_CREACION" + " )" + " VALUES (" + " S_PARAMETRO.NEXTVAL ," + " :idTipoParametro,"

					+ " :nombre,"

					+ " :descripcion,"

					+ " :valor,"

					+ " :usuarioCrea,"

					+ " :fechaCreacion"

					+ " )");

			if (listDTO == null || listDTO.size() == 0) {
				genericDao.create(entity, sql.toString());
			} else {
				throw javerianaExceptionService.throwException(Parameters.EXEPCION_001, null);
			}

		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServiceParametroService.create. Causa: " + e.getMessage() + ". Query: " + sql.toString());
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	@Transactional(rollbackFor = JaverianaException.class)
	public void update(ParametroDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");

		ParametroDTO objDTO = new ParametroDTO();
		objDTO.setNombre(entity.getNombre());
		ParametroDTO objDTOTMP = findByPK(entity);

		try {
			sql.append("UPDATE SCYD.PARAMETRO SET " + " ID_TIPO_PARAMETRO=:idTipoParametro," + " NOMBRE=:nombre," + " DESCRIPCION=:descripcion," + " VALOR=:valor,"
					 + " USUARIO_CREA=:usuarioCrea," + " FECHA_CREACION=:fechaCreacion" + " WHERE " + "ID_PARAMETRO=:idParametro");

			if (objDTOTMP.getNombre().equalsIgnoreCase(entity.getNombre())) {
				genericDao.update(entity, sql.toString());
			} else {
				List<ParametroDTO> listDTO = findByCriteria(objDTO);
				if (listDTO == null || listDTO.size() == 0) {
					genericDao.update(entity, sql.toString());
				} else {
					throw javerianaExceptionService.throwException(Parameters.EXEPCION_001, null);
				}
			}
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServiceParametroService.update. Causa: " + e.getMessage() + ". Query: " + sql.toString());
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	@Transactional(rollbackFor = JaverianaException.class)
	public void delete(ParametroDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("DELETE FROM SCYD.PARAMETRO " + " WHERE " + "ID_PARAMETRO=:idParametro");

			genericDao.delete(entity, sql.toString());
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServiceParametroService.delete. Causa: " + e.getMessage() + ". Query: " + sql.toString());
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	public List<ParametroDTO> findByCriteria(ParametroDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("select" + " ID_PARAMETRO," + " ID_TIPO_PARAMETRO," + " NOMBRE," + " DESCRIPCION," + " VALOR," + " USUARIO_CREA," + " FECHA_CREACION"
					+ " FROM SCYD.PARAMETRO " + " WHERE 1=1 ");
			if (entity.getIdParametro() != null)
				sql.append(" AND ID_PARAMETRO=:idParametro");
			if (entity.getIdTipoParametro() != null)
				sql.append(" AND ID_TIPO_PARAMETRO=:idTipoParametro");
			if (entity.getNombre() != null && !entity.getNombre().equals(""))
				sql.append(" AND NOMBRE=:nombre");
			if (entity.getDescripcion() != null && !entity.getDescripcion().equals(""))
				sql.append(" AND DESCRIPCION=:descripcion");
			if (entity.getValor() != null && !entity.getValor().equals(""))
				sql.append(" AND VALOR=:valor");
			if (entity.getUsuarioCrea() != null && !entity.getUsuarioCrea().equals(""))
				sql.append(" AND USUARIO_CREA=:usuarioCrea");
			if (entity.getFechaCreacion() != null)
				sql.append(" AND FECHA_CREACION=:fechaCreacion");

			return genericDao.findByCriteria(entity, sql.toString(), null, false);
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServiceParametroService.findByCriteria. Causa: " + e.getMessage() + ". Query: " + sql.toString());
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	public ParametroDTO findByPK(ParametroDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("select" + " ID_PARAMETRO," + " ID_TIPO_PARAMETRO," + " NOMBRE," + " DESCRIPCION," + " VALOR," + " USUARIO_CREA," + " FECHA_CREACION"
					+ " FROM SCYD.PARAMETRO " + " WHERE " + "ID_PARAMETRO=:idParametro");

			return (ParametroDTO) genericDao.findByPK(entity, sql.toString());
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServiceParametroService.findByCriteria. Causa: " + e.getMessage() + ". Query: " + sql.toString());
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	public List<ParametroDTO> findByCriteriaForeign(ParametroDTO entity) throws JaverianaException {
		StringBuilder select = new StringBuilder("");
		StringBuilder where = new StringBuilder("");
		List<ForaneaDTO> listForaneaDTO = new ArrayList<>();
		ForaneaDTO objForaneaDTO = null;
		try {
			select.append("" + " ID_PARAMETRO," + " ID_TIPO_PARAMETRO," + " NOMBRE," + " DESCRIPCION," + " VALOR," + " USUARIO_CREA," + " FECHA_CREACION");

			where.append(" WHERE 1=1 ");

			if (entity.getIdParametro() != null)
				where.append(" AND PARAMETRO.ID_PARAMETRO=:idParametro");
			if (entity.getIdTipoParametro() != null)
				where.append(" AND PARAMETRO.ID_TIPO_PARAMETRO=:idTipoParametro");
			if (entity.getNombre() != null && !entity.getNombre().equals(""))
				where.append(" AND PARAMETRO.NOMBRE=:nombre");
			if (entity.getDescripcion() != null && !entity.getDescripcion().equals(""))
				where.append(" AND PARAMETRO.DESCRIPCION=:descripcion");
			if (entity.getValor() != null && !entity.getValor().equals(""))
				where.append(" AND PARAMETRO.VALOR=:valor");
			if (entity.getUsuarioCrea() != null && !entity.getUsuarioCrea().equals(""))
				where.append(" AND PARAMETRO.USUARIO_CREA=:usuarioCrea");
			if (entity.getFechaCreacion() != null)
				where.append(" AND PARAMETRO.FECHA_CREACION=:fechaCreacion");

			objForaneaDTO = new ForaneaDTO();
			objForaneaDTO.setCampoForanea("ID_TIPO_PARAMETRO");
			objForaneaDTO.setIdForanea("ID_TIPO_PARAMETRO");
			objForaneaDTO.setTabla("TIPO_PARAMETRO");
			objForaneaDTO.setCampos("" + "ID_TIPO_PARAMETRO," + "NOMBRE," + "USUARIO_CREA");
			listForaneaDTO.add(objForaneaDTO);

			return genericDao.findByCriteriaForeign(entity, "PARAMETRO", select.toString(), where.toString(), listForaneaDTO, entity.getAliasForaneas());
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServiceParametroService.findByCriteria. Causa: " + e.getMessage() + ". Query: " + select.toString());
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}
	
	@Override
	public String findByValor(Long idParametro) throws JaverianaException{
         try{
			ParametroDTO parametro=new ParametroDTO();
			parametro.setIdParametro(idParametro);
			parametro=findByPK(parametro);
			return parametro!=null?parametro.getValor():null;				
		} catch(JaverianaException je) {
			throw je;
		} catch (Exception e) {
	    	bitacora.error("ServiceParametroService.findByPK. Causa: " + e.getMessage(),e);
	        throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}
}
