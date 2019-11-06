package $packageName.interfaces.services;

import java.util.List;
import $packageName.exception.JaverianaException;
import java.util.List;
import $packageName.entities.TytTipoParametroDTO;

/**
 * Clase service ITytTipoParametroService
 * @author bmsoftGenerator
 * @since V1.0
 */
public interface ITytTipoParametroService {
	/**
	 * Persistir un objeto en la DB
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a persistir
	 * @throws Exception
	 */
	public void create(TytTipoParametroDTO entity) throws JaverianaException;
	
	/**
	 * Actiualizar un objeto en la DB
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a actualizar
	 * @throws Exception
	 */
	public void update(TytTipoParametroDTO entity) throws JaverianaException;
	
	/**
	 * Borrar un objeto en la DB
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity object a borrar
	 * @throws Exception
	 */
	public void delete(TytTipoParametroDTO entity) throws JaverianaException;
	
	/**
	 * Obtener un listado de objetos usando un filtro
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public List<TytTipoParametroDTO> findByCriteria(TytTipoParametroDTO entity, String orderField,
			boolean isDesc) throws JaverianaException;
	
	/**
	 * Obtener una tupla usando un filtro
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public TytTipoParametroDTO findByPK(TytTipoParametroDTO entity) throws JaverianaException;
	
	
	/**
	 * Obtener una tupla usando un filtro
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity filtro a usar
	 * @throws Exception
	 */
	public List<TytTipoParametroDTO> findByCriteriaForeign(TytTipoParametroDTO entity) throws JaverianaException;


}