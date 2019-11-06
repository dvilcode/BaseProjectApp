package $packageName.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import $packageName.exception.JaverianaException;
import $packageName.interfaces.persistence.IGenericDAO;
import $packageName.interfaces.services.IJaverianaExceptionService;
import $packageName.utils.Parameters;
import $packageName.interfaces.services.ITytParametroService;
import java.io.Serializable;
import org.apache.log4j.Logger;
import java.util.List;
import $packageName.entities.TytParametroDTO;
import $packageName.entities.ForaneaDTO;
import java.util.ArrayList;

@Service("TytParametroService")
public class TytParametroService implements ITytParametroService{
	private static final long serialVersionUID = 1L;
	private static final Logger bitacora = Logger.getRootLogger();
	
	@Autowired
	private  IGenericDAO genericDao;
	@Autowired
	private IJaverianaExceptionService javerianaExceptionService;
	
	@Override
	@Transactional(rollbackFor= JaverianaException.class)
	public void create(TytParametroDTO entity) throws JaverianaException{
		StringBuilder sql = new StringBuilder("");
		try {
		
				   				   				                   validateName(entity,true); 
		   				   				   				   				   				
			sql.append("INSERT INTO TYT_PARAMETRO ("
			+" ID_PARAMETRO,"
			+" ID_TIPO_PARAMETRO,"
			+" NOMBRE,"
			+" DESCRIPCION,"
			+" VALOR,"
			+" USUARIO_CREA,"
			+" FECHA_CREACION"
		+" )"
		+ " VALUES ("
         +" S_TYT_PARAMETRO.NEXTVAL ," 
			+" :idTipoParametro,"

			+" :nombre,"

			+" :descripcion,"

			+" :valor,"

			+" :usuarioCrea,"

			+" :fechaCreacion"

		+" )");
		
		genericDao.create(entity, sql.toString());
		} catch(JaverianaException je) {
			throw je;
		} catch (Exception e) {
        	bitacora.error("ServiceTytParametroService.create. Causa: " + e.getMessage() + ". Query: " + sql.toString(),e);
            throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}
	
	@Override
	@Transactional(rollbackFor= JaverianaException.class)
	public void update(TytParametroDTO entity) throws JaverianaException{
		StringBuilder sql = new StringBuilder("");
		try {
		
				   				   				                   validateName(entity,false);
		   				   				   				   				   				
			sql.append("UPDATE TYT_PARAMETRO SET "
			+" ID_TIPO_PARAMETRO=:idTipoParametro,"
			+" NOMBRE=:nombre,"
			+" DESCRIPCION=:descripcion,"
			+" VALOR=:valor,"
			+" USUARIO_CREA=:usuarioCrea,"
			+" FECHA_CREACION=:fechaCreacion"
		+ " WHERE "
																	+"ID_PARAMETRO=:idParametro"																																					);
		
			genericDao.update(entity, sql.toString());
		} catch(JaverianaException je) {
			throw je;
		} catch (Exception e) {
        	bitacora.error("ServiceTytParametroService.update. Causa: " + e.getMessage() + ". Query: " + sql.toString(),e);
            throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}
	
	@Override
	@Transactional(rollbackFor= JaverianaException.class)
	public void delete(TytParametroDTO entity) throws JaverianaException{
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("DELETE FROM TYT_PARAMETRO "
				+ " WHERE "
																						+"ID_PARAMETRO=:idParametro"																																																					);
				
			genericDao.delete(entity, sql.toString());	
		} catch(JaverianaException je) {
			throw je;
		} catch (Exception e) {
        	bitacora.error("ServiceTytParametroService.delete. Causa: " + e.getMessage() + ". Query: " + sql.toString(),e);
            throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}
	
	@Override
	public List<TytParametroDTO> findByCriteria(TytParametroDTO entity, String orderField,
			boolean isDesc) throws JaverianaException{
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("select"
			+" ID_PARAMETRO,"
			+" ID_TIPO_PARAMETRO,"
			+" NOMBRE,"
			+" DESCRIPCION,"
			+" VALOR,"
			+" USUARIO_CREA,"
			+" FECHA_CREACION"
		+" FROM TYT_PARAMETRO "		
		+ " WHERE 1=1 ");
				         if(entity.getIdParametro()!=null    && entity.getIdParametro().intValue()!=0 )				 
				 				        sql.append(" AND ID_PARAMETRO=:idParametro");							
                   				         if(entity.getIdTipoParametro()!=null    && entity.getIdTipoParametro().intValue()!=0 )				 
				 				        sql.append(" AND ID_TIPO_PARAMETRO=:idTipoParametro");							
                   				         if(entity.getNombre()!=null   &&  !entity.getNombre().equals("") )				 
				 				       sql.append(" AND upper(NOMBRE) like  '%'||upper('%"+entity.getNombre()+"%')||'%'");						   
				   				         if(entity.getDescripcion()!=null   &&  !entity.getDescripcion().equals("") )				 
				 				       sql.append(" AND upper(DESCRIPCION) like  '%'||upper('%"+entity.getDescripcion()+"%')||'%'");						   
				   				         if(entity.getValor()!=null   &&  !entity.getValor().equals("") )				 
				 				       sql.append(" AND upper(VALOR) like  '%'||upper('%"+entity.getValor()+"%')||'%'");						   
				   				         if(entity.getUsuarioCrea()!=null   &&  !entity.getUsuarioCrea().equals("") )				 
				 				       sql.append(" AND upper(USUARIO_CREA) like  '%'||upper('%"+entity.getUsuarioCrea()+"%')||'%'");						   
				   				         if(entity.getFechaCreacion()!=null   )				 
				 				        sql.append(" AND FECHA_CREACION=:fechaCreacion");							
                   				
		
			return genericDao.findByCriteria(entity, sql.toString(),orderField,isDesc);		
		} catch(JaverianaException je) {
			throw je;
		} catch (Exception e) {
        	bitacora.error("ServiceTytParametroService.findByCriteria. Causa: " + e.getMessage() + ". Query: " + sql.toString(),e);
            throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}
	
	@Override
	public TytParametroDTO findByPK(TytParametroDTO entity) throws JaverianaException{
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("select"
		+" ID_PARAMETRO,"
		+" ID_TIPO_PARAMETRO,"
		+" NOMBRE,"
		+" DESCRIPCION,"
		+" VALOR,"
		+" USUARIO_CREA,"
		+" FECHA_CREACION"
		+" FROM TYT_PARAMETRO "

    + " WHERE "
																+"ID_PARAMETRO=:idParametro"																																					);		
		
			return (TytParametroDTO) genericDao.findByPK(entity, sql.toString());	
		} catch(JaverianaException je) {
			throw je;
		} catch (Exception e) {
        	bitacora.error("ServiceTytParametroService.findByPK. Causa: " + e.getMessage() + ". Query: " + sql.toString(),e);
            throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}




@Override
	public List<TytParametroDTO> findByCriteriaForeign(TytParametroDTO entity) throws JaverianaException{
		StringBuilder select = new StringBuilder("");
		StringBuilder where = new StringBuilder("");
		List<ForaneaDTO> listForaneaDTO  = new ArrayList<>();
		ForaneaDTO objForaneaDTO=null;
		try {
			select.append(""
			+" ID_PARAMETRO,"
			+" ID_TIPO_PARAMETRO,"
			+" NOMBRE,"
			+" DESCRIPCION,"
			+" VALOR,"
			+" USUARIO_CREA,"
			+" FECHA_CREACION"
  );		
		
		where.append(" WHERE 1=1 ");		
		
				         if(entity.getIdParametro()!=null   )
				     						where.append(" AND TYT_PARAMETRO.ID_PARAMETRO=:idParametro");							
                   					 
				         if(entity.getIdTipoParametro()!=null   )
				     						where.append(" AND TYT_PARAMETRO.ID_TIPO_PARAMETRO=:idTipoParametro");							
                   					 
				         if(entity.getNombre()!=null   &&  !entity.getNombre().equals(""))
				     				       where.append(" AND upper(TYT_PARAMETRO.NOMBRE) like  '%'||upper('%"+entity.getNombre()+"%')||'%'");                   							   
				   					 
				         if(entity.getDescripcion()!=null   &&  !entity.getDescripcion().equals(""))
				     				       where.append(" AND upper(TYT_PARAMETRO.DESCRIPCION) like  '%'||upper('%"+entity.getDescripcion()+"%')||'%'");                   							   
				   					 
				         if(entity.getValor()!=null   &&  !entity.getValor().equals(""))
				     				       where.append(" AND upper(TYT_PARAMETRO.VALOR) like  '%'||upper('%"+entity.getValor()+"%')||'%'");                   							   
				   					 
				         if(entity.getUsuarioCrea()!=null   &&  !entity.getUsuarioCrea().equals(""))
				     				       where.append(" AND upper(TYT_PARAMETRO.USUARIO_CREA) like  '%'||upper('%"+entity.getUsuarioCrea()+"%')||'%'");                   							   
				   					 
				         if(entity.getFechaCreacion()!=null   )
				     						where.append(" AND TYT_PARAMETRO.FECHA_CREACION=:fechaCreacion");							
                   					 
				
		     			     	 objForaneaDTO=new ForaneaDTO();				
	 objForaneaDTO.setCampoForanea("ID_TIPO_PARAMETRO");				
	 objForaneaDTO.setIdForanea("ID_TIPO_PARAMETRO");		      
	       	 	                objForaneaDTO.setTabla("TYT_TIPO_PARAMETRO");		 
   objForaneaDTO.setCampos(""	
	        +"ID_TIPO_PARAMETRO,"
						          +"NOMBRE,"
						          +"USUARIO_CREA"
						     ); 						 
						 		        				listForaneaDTO.add(objForaneaDTO);
            			     			     			     			     			     		    
		
			return genericDao.findByCriteriaForeign(entity, "TYT_PARAMETRO",select.toString(),where.toString(),listForaneaDTO,entity.getAliasForaneas());		
		} catch(JaverianaException je) {
			throw je;
		} catch (Exception e) {
        	bitacora.error("ServiceTytParametroService.findByCriteriaForeign. Causa: " + e.getMessage() + ". Query: " + select.toString(),e);
            throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}
	
	
	private void validateName(TytParametroDTO entity,boolean creating) throws JaverianaException{
	  StringBuilder sql = new StringBuilder("");
	  try{
	  sql.append("select 1 FROM TYT_PARAMETRO 	where nombre=:nombre");	  
	  sql.append(                   creating?"" :" and ID_PARAMETRO!=:idParametro "
				  			      			      			      			      			      			      			     );
	  
	    
		List<TytParametroDTO> list= genericDao.findByCriteria(entity, sql.toString(),null,false);	
		if(list!=null && list.size()>0)
		   throw javerianaExceptionService.throwException(Parameters.EXCEPCION_NAME, null);
	  
	  } catch(JaverianaException je) {
			throw je;
	  } catch (Exception e) {        	
            throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
	  }	
	}
}
