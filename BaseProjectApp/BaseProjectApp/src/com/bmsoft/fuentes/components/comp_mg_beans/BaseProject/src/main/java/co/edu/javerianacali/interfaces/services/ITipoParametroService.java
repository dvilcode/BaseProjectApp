package $packageName.interfaces.services;

import java.util.List;
import $packageName.exception.JaverianaException;
import java.util.List;
import $packageName.entities.TipoParametroDTO;

/**
 * Clase service ITipoParametroService
 * 
 * @author bmsoftGenerator
 * @since V1.0
 */
public interface ITipoParametroService {
	/**
	 * Persistir un objeto en la DB
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            object a persistir
	 * @throws Exception
	 */
	public void create(TipoParametroDTO entity) throws JaverianaException;

	/**
	 * Actiualizar un objeto en la DB
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            object a actualizar
	 * @throws Exception
	 */
	public void update(TipoParametroDTO entity) throws JaverianaException;

	/**
	 * Borrar un objeto en la DB
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            object a borrar
	 * @throws Exception
	 */
	public void delete(TipoParametroDTO entity) throws JaverianaException;

	/**
	 * Obtener un listado de objetos usando un filtro
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            filtro a usar
	 * @throws Exception
	 */
	public List<TipoParametroDTO> findByCriteria(TipoParametroDTO entity, String orderField, boolean isDesc) throws JaverianaException;

	/**
	 * Obtener una tupla usando un filtro
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            filtro a usar
	 * @throws Exception
	 */
	public TipoParametroDTO findByPK(TipoParametroDTO entity) throws JaverianaException;

	/**
	 * Obtener una tupla usando un filtro
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            filtro a usar
	 * @throws Exception
	 */
	public List<TipoParametroDTO> findByCriteriaForeign(TipoParametroDTO entity) throws JaverianaException;

}
