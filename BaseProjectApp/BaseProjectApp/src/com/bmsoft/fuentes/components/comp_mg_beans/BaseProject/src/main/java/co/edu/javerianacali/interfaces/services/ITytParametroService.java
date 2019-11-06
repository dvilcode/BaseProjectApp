package $packageName.interfaces.services;

import java.util.List;
import $packageName.exception.JaverianaException;
import java.util.List;
import $packageName.entities.TytParametroDTO;

/**
 * Clase service ITytParametroService
 * @author bmsoftGenerator
 * @since V1.0
 */
public interface ITytParametroService {
	/**
	 * Persistir un objeto en la DB
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a persistir
	 * @throws Exception
	 */
	public void create(TytParametroDTO entity) throws JaverianaException;
	
	/**
	 * Actiualizar un objeto en la DB
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a actualizar
	 * @throws Exception
	 */
	public void update(TytParametroDTO entity) throws JaverianaException;
	
	/**
	 * Borrar un objeto en la DB
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a borrar
	 * @throws Exception
	 */
	public void delete(TytParametroDTO entity) throws JaverianaException;
	
	/**
	 * Obtener un listado de objetos usando un filtro
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public List<TytParametroDTO> findByCriteria(TytParametroDTO entity, String orderField,
			boolean isDesc) throws JaverianaException;
	
	/**
	 * Obtener una tupla usando un filtro
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public TytParametroDTO findByPK(TytParametroDTO entity) throws JaverianaException;
	
	
	/**
	 * Obtener una tupla usando un filtro
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public List<TytParametroDTO> findByCriteriaForeign(TytParametroDTO entity) throws JaverianaException;


}