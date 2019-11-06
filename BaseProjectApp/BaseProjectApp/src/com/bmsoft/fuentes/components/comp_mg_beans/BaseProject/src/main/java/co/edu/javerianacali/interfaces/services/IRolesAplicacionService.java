package $packageName.interfaces.services;

import java.util.List;
import $packageName.exception.JaverianaException;
import java.util.List;
import $packageName.entities.RolesAplicacionDTO;

/**
 * Clase service IRolesAplicacionService
 * 
 * @author bmsoftGenerator
 * @since V1.0
 */
public interface IRolesAplicacionService {
	/**
	 * Persistir un objeto en la DB
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a persistir
	 * @throws Exception
	 */
	public void create(RolesAplicacionDTO entity) throws JaverianaException;

	/**
	 * Actiualizar un objeto en la DB
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a actualizar
	 * @throws Exception
	 */
	public void update(RolesAplicacionDTO entity) throws JaverianaException;

	/**
	 * Borrar un objeto en la DB
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a borrar
	 * @throws Exception
	 */
	public void delete(RolesAplicacionDTO entity) throws JaverianaException;

	/**
	 * Obtener un listado de objetos usando un filtro
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public List<RolesAplicacionDTO> findByCriteria(RolesAplicacionDTO entity, String orderField, boolean isDesc)
			throws JaverianaException;

	/**
	 * Obtener una tupla usando un filtro
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public RolesAplicacionDTO findByPK(RolesAplicacionDTO entity) throws JaverianaException;

	/**
	 * Obtener una tupla usando un filtro
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public List<RolesAplicacionDTO> findByCriteriaForeign(RolesAplicacionDTO entity) throws JaverianaException;
}