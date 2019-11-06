package $packageName.interfaces.services;

import java.math.BigDecimal;
import java.util.List;

import $packageName.entities.PermisoDTO;
import $packageName.entities.PermisosRolDTO;
import $packageName.entities.RolesAplicacionDTO;
import $packageName.exception.JaverianaException;

/**
 * Clase service IPermisosRolService
 * 
 * @author bmsoftGenerator
 * @since V1.0
 */
public interface IPermisosRolService {
	/**
	 * Persistir un objeto en la DB
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            object a persistir
	 * @throws Exception
	 */
	public BigDecimal create(PermisosRolDTO entity) throws JaverianaException;

	/**
	 * Actiualizar un objeto en la DB
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            object a actualizar
	 * @throws Exception
	 */
	public void update(PermisosRolDTO entity) throws JaverianaException;

	/**
	 * Borrar un objeto en la DB
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            object a borrar
	 * @throws Exception
	 */
	public void delete(PermisosRolDTO entity) throws JaverianaException;

	/**
	 * Obtener un listado de objetos usando un filtro
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            filtro a usar
	 * @throws Exception
	 */
	public List<PermisosRolDTO> findByCriteria(PermisosRolDTO entity) throws JaverianaException;

	/**
	 * Obtener una tupla usando un filtro
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            filtro a usar
	 * @throws Exception
	 */
	public PermisosRolDTO findByPK(PermisosRolDTO entity) throws JaverianaException;

	/**
	 * Obtener una tupla usando un filtro
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            filtro a usar
	 * @throws Exception
	 */
	public List<PermisosRolDTO> findByCriteriaForeign(PermisosRolDTO entity) throws JaverianaException;

	/**
	 * 
	 * @param selectedRol
	 * @param filter
	 * @return
	 * @throws JaverianaException
	 */
	public List<PermisosRolDTO> findPermisosForRol(RolesAplicacionDTO selectedRol, PermisoDTO filter) throws JaverianaException;

	/**
	 * Asociar un rol con sus respectivos permisos
	 * 
	 * @param selectedRol
	 * @param permisosRol
	 * @param idUsuarioCrea
	 * @throws JaverianaException
	 */
	public void asociarRolesConPermisos(RolesAplicacionDTO selectedRol, List<PermisosRolDTO> permisosRol, String idUsuarioCrea) throws JaverianaException;

	/**
	 * Cargar los permisos de la aplicacion en cache
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @throws JaverianaException
	 */
	public void cargarPermisos() throws JaverianaException;

	/**
	 * Actualizar los permisos de la aplicacion en cache
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @throws JaverianaException
	 */
	public void actualizarPermisos() throws JaverianaException;

	/**
	 * Obtiene un objeto registrado en el cache dado
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @param nameCache
	 *            nombre del cache donde se obtendra el objeto
	 * @param idRecurso
	 *            identificador del objeto en el cache
	 * @throws JaverianaException
	 */
	public Object getPermiso(String nameCache, String idRecurso) throws JaverianaException;
}
