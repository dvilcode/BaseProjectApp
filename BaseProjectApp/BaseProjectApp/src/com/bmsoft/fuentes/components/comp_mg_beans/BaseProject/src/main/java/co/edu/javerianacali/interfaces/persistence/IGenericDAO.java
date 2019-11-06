package $packageName.interfaces.persistence;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import $packageName.entities.ForaneaDTO;
import $packageName.exception.JaverianaException;

/**
 * Clase generica para ejecutar DML en la DB
 * 
 * @author jhon.andrey
 * @since V1.0
 */
public interface IGenericDAO {
	/**
	 * Persistir un object en la DB
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @param entity
	 *            objeto a persistir
	 * @param sql
	 *            sql insert a ejecutar
	 * @throws Exception
	 */
	void create(Object entity, String sql) throws JaverianaException;

	/**
	 * Persistir un object en la DB
	 * 
	 * @author wilferac
	 * @since V1.0
	 * @param entity
	 *            objeto a persistir
	 * @param sql
	 *            sql insert a ejecutar
	 * @throws Exception
	 * @return id del objeto insertado
	 */
	public BigDecimal create(Object entity, String sql, String[] columnNames) throws JaverianaException;

	/**
	 * Actualizar un object en la DB
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @param entity
	 *            objeto a actualizar
	 * @param sql
	 *            sql update a ejecutar
	 * @throws Exception
	 */
	void update(Object entity, String sql) throws JaverianaException;

	/**
	 * Borrar un object de la DB
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @param entity
	 *            objeto a borrar
	 * @param sql
	 *            sql delete a ejecutar
	 * @throws Exception
	 */
	public void delete(Object entity, String sql) throws JaverianaException;

	/**
	 * Encontrar todos objetos que cumplan con los criterios deseados
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @param entity
	 *            objeto filtro
	 * @param sql
	 *            sql select a ejecutar
	 * @throws Exception
	 */
	public List findByCriteria(Object entity, String sql, String order, boolean isDesc) throws JaverianaException;

	/**
	 * Registrar evento en el log
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @param usuario
	 * @param emplid
	 * @param accion
	 * @param texto
	 * @throws Exception
	 */
	void registerLog(String usuario, String emplid, String accion, String texto) throws JaverianaException;

	/**
	 * Encontrar el objeto por su PK
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @param entity
	 *            objeto filtro con la PK a buscar
	 * @param sql
	 *            sql select a ejecutar
	 * @throws Exception
	 */
	Object findByPK(Object entity, String sql) throws JaverianaException;

	/**
	 * Consultar los registro de una tabla incluyendo campos de sus llaves
	 * foraneas
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @param entity
	 *            objeto filtro
	 * @param tabla
	 *            nombre de la tabla padre
	 * @param select
	 *            sentencia select con los campos de la tabla padre y las
	 *            foraneas que se quieren seleccionar
	 * @param where
	 *            los filtros que se quieren aplicar a la consulta. Opcional
	 * @param foraneasList
	 *            lista con los datos de las tablas foraneas a considerar en la
	 *            consulta
	 * @throws Exception
	 */
	public List findByCriteriaForeign(Object entity, String tabla, String select, String where, List<ForaneaDTO> foraneasList, HashMap<String, String> aliasForaneas)
			throws JaverianaException;

	/**
	 * Consulta las foraneas por un query especifico.
	 * 
	 * @param entity,
	 *            String sql
	 * @author Dv
	 */
	public List findByQueryForeign(Object entity, String sql) throws JaverianaException;

	/**
	 * get a raw list from the database
	 * 
	 * @param sql
	 * @return
	 * @throws JaverianaException
	 */
	public List<Map<String, Object>> findByCriteria(String sql) throws JaverianaException;

	/**
	 * Encontrar el objeto
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @param entity
	 *            objeto filtro con la PK a buscar
	 * @param sql
	 *            sql select a ejecutar
	 * @throws Exception
	 */
	public Map<String, Object> findByPKGeneral(String sql, Map<String, ?> parametros) throws JaverianaException;

}
