package $packageName.services;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import $packageName.entities.ForaneaDTO;
import $packageName.entities.PermisoDTO;
import $packageName.entities.PermisosRolDTO;
import $packageName.entities.RolesAplicacionDTO;
import $packageName.exception.JaverianaException;
import $packageName.interfaces.persistence.IGenericDAO;
import $packageName.interfaces.services.IJaverianaExceptionService;
import $packageName.interfaces.services.IPermisosRolService;
import $packageName.utils.Parameters;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Service("PermisosRolService")
public class PermisosRolService implements IPermisosRolService {
	private static final long serialVersionUID = 1L;
	private static final Logger bitacora = Logger.getRootLogger();

	@Autowired
	private IGenericDAO genericDao;
	@Autowired
	private IJaverianaExceptionService javerianaExceptionService;

	@Override
	@Transactional(rollbackFor = JaverianaException.class)
	public BigDecimal create(PermisosRolDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("INSERT INTO NUEVO_PARQUEADERO.PERMISOS_ROL (" + " ID_PERMISO_ROL," + " ID_PERMISO," + " ID_ROL," + " VALOR,"
					+ " USUARIO_CREA," + " FECHA_CREACION" + " )" + " VALUES (" + " S_PERMISOS_ROL.NEXTVAL ,"
					+ " :idPermiso," + " :idRol," + " :valor," + " :usuarioCrea," + " :fechaCreacion" + " )");

			String[] colum = new String[1];
			colum[0] = "ID_PERMISO_ROL";

			return genericDao.create(entity, sql.toString(), colum);
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServicePermisosRolService.create. Causa: " + e.getMessage() + ". Query: " + sql.toString(),
					e);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	@Transactional(rollbackFor = JaverianaException.class)
	public void update(PermisosRolDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("UPDATE NUEVO_PARQUEADERO.PERMISOS_ROL SET " + " ID_PERMISO=:idPermiso," + " ID_ROL=:idRol,"
					+ " VALOR=:valor," + " USUARIO_CREA=:usuarioCrea," + " FECHA_CREACION=:fechaCreacion" + " WHERE "
					+ "ID_PERMISO_ROL=:idPermisoRol");

			genericDao.update(entity, sql.toString());
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServicePermisosRolService.update. Causa: " + e.getMessage() + ". Query: " + sql.toString());
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	@Transactional(rollbackFor = JaverianaException.class)
	public void delete(PermisosRolDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			Boolean addFilter = false;
			sql.append("DELETE FROM NUEVO_PARQUEADERO.PERMISOS_ROL " + " WHERE 1=1 ");
			if (entity.getIdRol() != null) {
				sql.append(" AND ID_ROL = :IdRol");
				addFilter = true;
			}
			if (entity.getIdPermiso() != null) {
				sql.append(" AND ID_PERMISO = :IdPermiso");
				addFilter = true;
			}

			if (addFilter) {// at least one filter
				genericDao.delete(entity, sql.toString());
			}
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServicePermisosRolService.delete. Causa: " + e.getMessage() + ". Query: " + sql.toString(),
					e);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	public List<PermisosRolDTO> findByCriteria(PermisosRolDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("select" + " ID_PERMISO_ROL," + " ID_PERMISO," + " ID_ROL," + " VALOR," + " USUARIO_CREA,"
					+ " FECHA_CREACION" + " FROM NUEVO_PARQUEADERO.PERMISOS_ROL " + " WHERE 1=1 ");
			if (entity.getIdPermisoRol() != null)
				sql.append(" AND ID_PERMISO_ROL=:idPermisoRol");
			if (entity.getIdPermiso() != null)
				sql.append(" AND ID_PERMISO=:idPermiso");
			if (entity.getIdRol() != null)
				sql.append(" AND ID_ROL=:idRol");
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
			bitacora.error("ServicePermisosRolService.findByCriteria. Causa: " + e.getMessage() + ". Query: "
					+ sql.toString());
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	public PermisosRolDTO findByPK(PermisosRolDTO entity) throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("select" + " ID_PERMISO_ROL," + " ID_PERMISO," + " ID_ROL," + " VALOR," + " USUARIO_CREA,"
					+ " FECHA_CREACION" + " FROM NUEVO_PARQUEADERO.PERMISOS_ROL "

					+ " WHERE " + "ID_PERMISO_ROL=:idPermisoRol");

			return (PermisosRolDTO) genericDao.findByPK(entity, sql.toString());
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServicePermisosRolService.findByCriteria. Causa: " + e.getMessage() + ". Query: "
					+ sql.toString());
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	public List<PermisosRolDTO> findByCriteriaForeign(PermisosRolDTO entity) throws JaverianaException {
		StringBuilder select = new StringBuilder("");
		StringBuilder where = new StringBuilder("");
		List<ForaneaDTO> listForaneaDTO = new ArrayList<>();
		ForaneaDTO objForaneaDTO = null;
		try {
			select.append("" + " ID_PERMISO_ROL," + " ID_PERMISO," + " ID_ROL," + " VALOR," + " USUARIO_CREA,"
					+ " FECHA_CREACION");

			where.append(" WHERE 1=1 ");

			if (entity.getIdPermisoRol() != null)
				where.append(" AND PERMISOS_ROL.ID_PERMISO_ROL=:idPermisoRol");
			if (entity.getIdPermiso() != null)
				where.append(" AND PERMISOS_ROL.ID_PERMISO=:idPermiso");
			if (entity.getIdRol() != null)
				where.append(" AND PERMISOS_ROL.ID_ROL=:idRol");
			if (entity.getValor() != null && !entity.getValor().equals(""))
				where.append(" AND PERMISOS_ROL.VALOR=:valor");
			if (entity.getUsuarioCrea() != null && !entity.getUsuarioCrea().equals(""))
				where.append(" AND PERMISOS_ROL.USUARIO_CREA=:usuarioCrea");
			if (entity.getFechaCreacion() != null)
				where.append(" AND PERMISOS_ROL.FECHA_CREACION=:fechaCreacion");

			objForaneaDTO = new ForaneaDTO();
			objForaneaDTO.setCampoForanea("ID_PERMISO");
			objForaneaDTO.setIdForanea("ID_PERMISO");
			objForaneaDTO.setTabla("PERMISO");
			objForaneaDTO.setCampos("" + "ID_PERMISO," + "ID_TIPO_PERMISO," + "NOMBRE," + "USUARIO_CREA");
			listForaneaDTO.add(objForaneaDTO);
			objForaneaDTO = new ForaneaDTO();
			objForaneaDTO.setCampoForanea("ID_ROL");
			objForaneaDTO.setIdForanea("ID_ROL");
			objForaneaDTO.setTabla("ROLES_APLICACION");
			objForaneaDTO.setCampos("" + "ID_ROL," + "NOMBRE," + "ROLES_PEOPLE," + "USUARIO_CREA");
			listForaneaDTO.add(objForaneaDTO);

			return genericDao.findByCriteriaForeign(entity, "PERMISOS_ROL", select.toString(), where.toString(),
					listForaneaDTO, entity.getAliasForaneas());
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("ServicePermisosRolService.findByCriteria. Causa: " + e.getMessage() + ". Query: "
					+ select.toString());
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Override
	public List<PermisosRolDTO> findPermisosForRol(RolesAplicacionDTO selectedRol, PermisoDTO filter)
			throws JaverianaException {
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("select permisos_rol.ID_PERMISO_ROL ID_PERMISO_ROL,\r\n"
					+ "                permisos_rol.ID_ROL ID_ROL,\r\n"
					+ "                permisos_rol.VALOR VALOR,\r\n"
					+ "                permisos_rol.USUARIO_CREA USUARIO_CREA,\r\n"
					+ "                permisos_rol.USUARIO_CREA USUARIO_CREA,\r\n"
					+ "                permiso.id_permiso ID_PERMISO,\r\n"
					+ "                permiso.nombre nombreRol,\r\n"
					+ "                permiso.descripcion descripcionRol,\r\n"
					+ "                nvl2(permisos_rol.id_rol, 1, 0) isSelected\r\n"
					+ "                from permiso\r\n"
					+ "                left join permisos_rol ON permiso.id_permiso = permisos_rol.id_permiso AND permisos_rol.id_rol = "
					+ selectedRol.getIdRol() + " WHERE 1=1 ");
			if (filter.getNombre() != null && filter.getNombre().trim().compareTo("") != 0) {
				sql.append(" AND lower(permiso.nombre) like lower('%" + filter.getNombre() + "%')");
			}

			if (filter.getIdTipoPermiso() != null) {
				sql.append("AND permiso.id_tipo_permiso = " + filter.getIdTipoPermiso());
			}

			return genericDao.findByCriteria(new PermisosRolDTO(), sql.toString(), null, false);
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error(
					"ServicePermisosRolService.findByCriteria. Causa: " + e.getMessage() + ". Query: " + sql.toString(),
					e);
			throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}

	@Transactional(rollbackFor = JaverianaException.class)
	@Override
	public void asociarRolesConPermisos(RolesAplicacionDTO selectedRol, List<PermisosRolDTO> permisosRol,
			String idUsuarioCrea) throws JaverianaException {
		PermisosRolDTO entity = null;

		for (PermisosRolDTO permisoRol : permisosRol) {
			/** delete the old config **/
			entity = new PermisosRolDTO();
			if (!permisoRol.getIsSelected() && permisoRol.getIdPermisoRol() != null) {
				entity.setIdRol(selectedRol.getIdRol());
				entity.setIdPermiso(permisoRol.getIdPermiso());

				delete(entity);
			} else if (permisoRol.getIsSelected()) {
				PermisosRolDTO objPermisoRol = new PermisosRolDTO();
				objPermisoRol.setIdRol(selectedRol.getIdRol());
				objPermisoRol.setIdPermiso(permisoRol.getIdPermiso());
				if (findByCriteria(objPermisoRol).size() == 0) {
					entity.setIdRol(selectedRol.getIdRol());
					entity.setIdPermiso(permisoRol.getIdPermiso());
					entity.setFechaCreacion(new Date());
					entity.setUsuarioCrea(idUsuarioCrea);
					entity.setValor(Parameters.VALOR_PERMISO_ROL);

					BigDecimal idGen = create(entity);
					permisoRol.setIdPermisoRol(idGen);
				}

			}
		}

	}

	public void cargarPermisos() throws JaverianaException {
		PermisosRolDTO entity = new PermisosRolDTO();
		Cache cache = null;
		List<PermisosRolDTO> listPermisos = null;
		try {
			cache = getCache(Parameters.CACHE_PERMISOS);

			listPermisos = findByCriteria(entity);
			if (listPermisos != null && !listPermisos.isEmpty()) {
				for (PermisosRolDTO entityList : listPermisos) {
					if (entityList.getValor() != null && entityList.getValor().equals("S")) {
						Element element = new Element(entityList.getIdRol() + "-" + entityList.getIdPermiso(), true);
						cache.put(element);
					} else if (entityList.getValor() != null && entityList.getValor().equals("N")) {
						Element element = new Element(entityList.getIdRol() + "-" + entityList.getIdPermiso(), false);
						cache.put(element);
					} else {
						Element element = new Element(entityList.getIdRol() + "-" + entityList.getIdPermiso(),
								entityList.getValor());
						cache.put(element);
					}
				}
			}
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("PermisosRolService.cargarPermisos Causa: " + e.getMessage());
			throw javerianaExceptionService.throwException("base02", null);
		}
	}

	public void actualizarPermisos() throws JaverianaException {
		PermisosRolDTO entity = new PermisosRolDTO();
		Cache cache = null;
		List<PermisosRolDTO> listPermisos = null;
		try {
			cache = getCache(Parameters.CACHE_PERMISOS);
			listPermisos = findByCriteria(entity);
			List<Element> nuevosElementos = new ArrayList<Element>();
			if (listPermisos != null && listPermisos.size() > 0) {
				for (PermisosRolDTO entityList : listPermisos) {
					if (entityList.getValor() != null && entityList.getValor().equals("S")) {
						Element element = new Element(entityList.getIdRol() + "-" + entityList.getIdPermiso(), true);
						nuevosElementos.add(element);
					} else if (entityList.getValor() != null && entityList.getValor().equals("N")) {
						Element element = new Element(entityList.getIdRol() + "-" + entityList.getIdPermiso(), false);
						nuevosElementos.add(element);
					} else {
						Element element = new Element(entityList.getIdRol() + "-" + entityList.getIdPermiso(),
								entityList.getValor());
						nuevosElementos.add(element);
					}

				}

				// Se limpia el cache actual y se actualiza con los nuevos
				// elementos
				cache.removeAll();
				cache.putAll(nuevosElementos);
			}

		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("PermisosRolService.actualizarPermisos Causa: " + e.getMessage());
			throw javerianaExceptionService.throwException("base02", null);
		}
	}

	public Object getPermiso(String nameCache, String idRecurso) throws JaverianaException {
		Cache cache = null;
		try {
			cache = getCache(nameCache);
			return cache.get(idRecurso) != null ? cache.get(idRecurso).getObjectValue() : null;
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("PermisosRolService.getRecurso Causa: " + e.getMessage());
			throw javerianaExceptionService.throwException("base02", null);
		}
	}

	private Cache getCache(String nameCache) throws Exception {
		CacheManager manager = null;
		ClassLoader contextClassLoader = null;
		InputStream resourceAsStream = null;
		contextClassLoader = Thread.currentThread().getContextClassLoader();
		resourceAsStream = contextClassLoader.getResourceAsStream("ehcache.xml");
		manager = CacheManager.create(resourceAsStream);
		return manager.getCache(nameCache);
	}
}
