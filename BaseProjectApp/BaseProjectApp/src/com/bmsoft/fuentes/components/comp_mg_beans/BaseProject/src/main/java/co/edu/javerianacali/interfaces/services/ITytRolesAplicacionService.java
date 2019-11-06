package $packageName.interfaces.services;

import java.util.List;
import $packageName.exception.JaverianaException;
import java.util.List;
import $packageName.entities.TytRolesAplicacionDTO;

/**
 * Clase service ITytRolesAplicacionService
 * @author bmsoftGenerator
 * @since V1.0
 */
public interface ITytRolesAplicacionService {
	/**
	 * Persistir un objeto en la DB
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a persistir
	 * @throws Exception
	 */
	public void create(TytRolesAplicacionDTO entity) throws JaverianaException;
	
	/**
	 * Actiualizar un objeto en la DB
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a actualizar
	 * @throws Exception
	 */
	public void update(TytRolesAplicacionDTO entity) throws JaverianaException;
	
	/**
	 * Borrar un objeto en la DB
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a borrar
	 * @throws Exception
	 */
	public void delete(TytRolesAplicacionDTO entity) throws JaverianaException;
	
	/**
	 * Obtener un listado de objetos usando un filtro
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public List<TytRolesAplicacionDTO> findByCriteria(TytRolesAplicacionDTO entity, String orderField,
			boolean isDesc) throws JaverianaException;
	
	/**
	 * Obtener una tupla usando un filtro
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public TytRolesAplicacionDTO findByPK(TytRolesAplicacionDTO entity) throws JaverianaException;
	
	
	/**
	 * Obtener una tupla usando un filtro
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public List<TytRolesAplicacionDTO> findByCriteriaForeign(TytRolesAplicacionDTO entity) throws JaverianaException;


}