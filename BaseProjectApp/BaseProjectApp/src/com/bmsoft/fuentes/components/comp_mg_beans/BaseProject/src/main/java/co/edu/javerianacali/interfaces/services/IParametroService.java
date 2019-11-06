package $packageName.interfaces.services;

import java.util.List;

import $packageName.entities.ParametroDTO;
import $packageName.exception.JaverianaException;

/**
 * Clase service IParametroService
 * 
 * @author bmsoftGenerator
 * @since V1.0
 */
public interface IParametroService {
	/**
	 * Persistir un objeto en la DB
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            object a persistir
	 * @throws Exception
	 */
	public void create(ParametroDTO entity) throws JaverianaException;

	/**
	 * Actiualizar un objeto en la DB
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            object a actualizar
	 * @throws Exception
	 */
	public void update(ParametroDTO entity) throws JaverianaException;

	/**
	 * Borrar un objeto en la DB
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            object a borrar
	 * @throws Exception
	 */
	public void delete(ParametroDTO entity) throws JaverianaException;

	/**
	 * Obtener un listado de objetos usando un filtro
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            filtro a usar
	 * @throws Exception
	 */
	public List<ParametroDTO> findByCriteria(ParametroDTO entity) throws JaverianaException;

	/**
	 * Obtener una tupla usando un filtro
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            filtro a usar
	 * @throws Exception
	 */
	public ParametroDTO findByPK(ParametroDTO entity) throws JaverianaException;

	/**
	 * Obtener una tupla usando un filtro
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 * @param entity
	 *            filtro a usar
	 * @throws Exception
	 */
	public List<ParametroDTO> findByCriteriaForeign(ParametroDTO entity) throws JaverianaException;
	
	/**	 
	 * @param idParametro
	 * @return
	 * @throws JaverianaException
	 */
	public String findByValor(Long idParametro) throws JaverianaException;    

}
