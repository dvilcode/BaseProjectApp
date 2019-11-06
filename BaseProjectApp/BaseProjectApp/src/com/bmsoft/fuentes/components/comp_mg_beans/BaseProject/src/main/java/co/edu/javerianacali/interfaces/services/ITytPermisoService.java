package $packageName.interfaces.services;

import java.util.List;
import $packageName.exception.JaverianaException;
import java.util.List;
import $packageName.entities.TytPermisoDTO;

/**
 * Clase service ITytPermisoService
 * @author bmsoftGenerator
 * @since V1.0
 */
public interface ITytPermisoService {
	/**
	 * Persistir un objeto en la DB
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a persistir
	 * @throws Exception
	 */
	public void create(TytPermisoDTO entity) throws JaverianaException;
	
	/**
	 * Actiualizar un objeto en la DB
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a actualizar
	 * @throws Exception
	 */
	public void update(TytPermisoDTO entity) throws JaverianaException;
	
	/**
	 * Borrar un objeto en la DB
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a borrar
	 * @throws Exception
	 */
	public void delete(TytPermisoDTO entity) throws JaverianaException;
	
	/**
	 * Obtener un listado de objetos usando un filtro
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public List<TytPermisoDTO> findByCriteria(TytPermisoDTO entity, String orderField,
			boolean isDesc) throws JaverianaException;
	
	/**
	 * Obtener una tupla usando un filtro
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public TytPermisoDTO findByPK(TytPermisoDTO entity) throws JaverianaException;
	
	
	/**
	 * Obtener una tupla usando un filtro
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public List<TytPermisoDTO> findByCriteriaForeign(TytPermisoDTO entity) throws JaverianaException;


}