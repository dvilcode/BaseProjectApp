package $packageName.interfaces.services;

import java.util.List;
import $packageName.exception.JaverianaException;
import java.util.List;
import $packageName.entities.TytPermisosRolDTO;

/**
 * Clase service ITytPermisosRolService
 * @author bmsoftGenerator
 * @since V1.0
 */
public interface ITytPermisosRolService {
	/**
	 * Persistir un objeto en la DB
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a persistir
	 * @throws Exception
	 */
	public void create(TytPermisosRolDTO entity) throws JaverianaException;
	
	/**
	 * Actiualizar un objeto en la DB
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a actualizar
	 * @throws Exception
	 */
	public void update(TytPermisosRolDTO entity) throws JaverianaException;
	
	/**
	 * Borrar un objeto en la DB
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a borrar
	 * @throws Exception
	 */
	public void delete(TytPermisosRolDTO entity) throws JaverianaException;
	
	/**
	 * Obtener un listado de objetos usando un filtro
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public List<TytPermisosRolDTO> findByCriteria(TytPermisosRolDTO entity, String orderField,
			boolean isDesc) throws JaverianaException;
	
	/**
	 * Obtener una tupla usando un filtro
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public TytPermisosRolDTO findByPK(TytPermisosRolDTO entity) throws JaverianaException;
	
	
	/**
	 * Obtener una tupla usando un filtro
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public List<TytPermisosRolDTO> findByCriteriaForeign(TytPermisosRolDTO entity) throws JaverianaException;


}