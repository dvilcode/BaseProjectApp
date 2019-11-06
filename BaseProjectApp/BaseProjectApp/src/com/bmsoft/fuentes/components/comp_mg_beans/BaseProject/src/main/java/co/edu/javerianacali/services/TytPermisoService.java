package $packageName.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import $packageName.exception.JaverianaException;
import $packageName.interfaces.persistence.IGenericDAO;
import $packageName.interfaces.services.IJaverianaExceptionService;
import $packageName.utils.Parameters;
import $packageName.interfaces.services.ITytPermisoService;
import java.io.Serializable;
import org.apache.log4j.Logger;
import java.util.List;
import $packageName.entities.TytPermisoDTO;
import $packageName.entities.ForaneaDTO;
import java.util.ArrayList;

@Service("TytPermisoService")
public class TytPermisoService implements ITytPermisoService{
	private static final long serialVersionUID = 1L;
	private static final Logger bitacora = Logger.getRootLogger();
	
	@Autowired
	private  IGenericDAO genericDao;
	@Autowired
	private IJaverianaExceptionService javerianaExceptionService;
	
	@Override
	@Transactional(rollbackFor= JaverianaException.class)
	public void create(TytPermisoDTO entity) throws JaverianaException{
		StringBuilder sql = new StringBuilder("");
		try {
		
				   				   				                   validateName(entity,true); 
		   				   				   				   				   				
			sql.append("INSERT INTO TYT_PERMISO ("
			+" ID_PERMISO,"
			+" ID_TIPO_PERMISO,"
			+" NOMBRE,"
			+" DESCRIPCION,"
			+" USUARIO_CREA,"
			+" ACTIVO,"
			+" FECHA_CREACION"
		+" )"
		+ " VALUES ("
         +" S_TYT_PERMISO.NEXTVAL ," 
			+" :idTipoPermiso,"

			+" :nombre,"

			+" :descripcion,"

			+" :usuarioCrea,"

			+" :activo,"

			+" :fechaCreacion"

		+" )");
		
		genericDao.create(entity, sql.toString());
		} catch(JaverianaException je) {
			throw je;
		} catch (Exception e) {
        	bitacora.error("ServiceTytPermisoService.create. Causa: " + e.getMessage() + ". Query: " + sql.toString(),e);
            throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}
	
	@Override
	@Transactional(rollbackFor= JaverianaException.class)
	public void update(TytPermisoDTO entity) throws JaverianaException{
		StringBuilder sql = new StringBuilder("");
		try {
		
				   				   				                   validateName(entity,false);
		   				   				   				   				   				
			sql.append("UPDATE TYT_PERMISO SET "
			+" ID_TIPO_PERMISO=:idTipoPermiso,"
			+" NOMBRE=:nombre,"
			+" DESCRIPCION=:descripcion,"
			+" USUARIO_CREA=:usuarioCrea,"
			+" ACTIVO=:activo,"
			+" FECHA_CREACION=:fechaCreacion"
		+ " WHERE "
																	+"ID_PERMISO=:idPermiso"																																					);
		
			genericDao.update(entity, sql.toString());
		} catch(JaverianaException je) {
			throw je;
		} catch (Exception e) {
        	bitacora.error("ServiceTytPermisoService.update. Causa: " + e.getMessage() + ". Query: " + sql.toString(),e);
            throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}
	
	@Override
	@Transactional(rollbackFor= JaverianaException.class)
	public void delete(TytPermisoDTO entity) throws JaverianaException{
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("DELETE FROM TYT_PERMISO "
				+ " WHERE "
																						+"ID_PERMISO=:idPermiso"																																																					);
				
			genericDao.delete(entity, sql.toString());	
		} catch(JaverianaException je) {
			throw je;
		} catch (Exception e) {
        	bitacora.error("ServiceTytPermisoService.delete. Causa: " + e.getMessage() + ". Query: " + sql.toString(),e);
            throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}
	
	@Override
	public List<TytPermisoDTO> findByCriteria(TytPermisoDTO entity, String orderField,
			boolean isDesc) throws JaverianaException{
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("select"
			+" ID_PERMISO,"
			+" ID_TIPO_PERMISO,"
			+" NOMBRE,"
			+" DESCRIPCION,"
			+" USUARIO_CREA,"
			+" ACTIVO,"
			+" FECHA_CREACION"
		+" FROM TYT_PERMISO "		
		+ " WHERE 1=1 ");
				         if(entity.getIdPermiso()!=null    && entity.getIdPermiso().intValue()!=0 )				 
				 				        sql.append(" AND ID_PERMISO=:idPermiso");							
                   				         if(entity.getIdTipoPermiso()!=null    && entity.getIdTipoPermiso().intValue()!=0 )				 
				 				        sql.append(" AND ID_TIPO_PERMISO=:idTipoPermiso");							
                   				         if(entity.getNombre()!=null   &&  !entity.getNombre().equals("") )				 
				 				       sql.append(" AND upper(NOMBRE) like  '%'||upper('%"+entity.getNombre()+"%')||'%'");						   
				   				         if(entity.getDescripcion()!=null   &&  !entity.getDescripcion().equals("") )				 
				 				       sql.append(" AND upper(DESCRIPCION) like  '%'||upper('%"+entity.getDescripcion()+"%')||'%'");						   
				   				         if(entity.getUsuarioCrea()!=null   &&  !entity.getUsuarioCrea().equals("") )				 
				 				       sql.append(" AND upper(USUARIO_CREA) like  '%'||upper('%"+entity.getUsuarioCrea()+"%')||'%'");						   
				   				         if(entity.getActivo()!=null   )				 
				 				        sql.append(" AND ACTIVO=:activo");							
                   				         if(entity.getFechaCreacion()!=null   )				 
				 				        sql.append(" AND FECHA_CREACION=:fechaCreacion");							
                   				
		
			return genericDao.findByCriteria(entity, sql.toString(),orderField,isDesc);		
		} catch(JaverianaException je) {
			throw je;
		} catch (Exception e) {
        	bitacora.error("ServiceTytPermisoService.findByCriteria. Causa: " + e.getMessage() + ". Query: " + sql.toString(),e);
            throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}
	
	@Override
	public TytPermisoDTO findByPK(TytPermisoDTO entity) throws JaverianaException{
		StringBuilder sql = new StringBuilder("");
		try {
			sql.append("select"
		+" ID_PERMISO,"
		+" ID_TIPO_PERMISO,"
		+" NOMBRE,"
		+" DESCRIPCION,"
		+" USUARIO_CREA,"
		+" ACTIVO,"
		+" FECHA_CREACION"
		+" FROM TYT_PERMISO "

    + " WHERE "
																+"ID_PERMISO=:idPermiso"																																					);		
		
			return (TytPermisoDTO) genericDao.findByPK(entity, sql.toString());	
		} catch(JaverianaException je) {
			throw je;
		} catch (Exception e) {
        	bitacora.error("ServiceTytPermisoService.findByPK. Causa: " + e.getMessage() + ". Query: " + sql.toString(),e);
            throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}




@Override
	public List<TytPermisoDTO> findByCriteriaForeign(TytPermisoDTO entity) throws JaverianaException{
		StringBuilder select = new StringBuilder("");
		StringBuilder where = new StringBuilder("");
		List<ForaneaDTO> listForaneaDTO  = new ArrayList<>();
		ForaneaDTO objForaneaDTO=null;
		try {
			select.append(""
			+" ID_PERMISO,"
			+" ID_TIPO_PERMISO,"
			+" NOMBRE,"
			+" DESCRIPCION,"
			+" USUARIO_CREA,"
			+" ACTIVO,"
			+" FECHA_CREACION"
  );		
		
		where.append(" WHERE 1=1 ");		
		
				         if(entity.getIdPermiso()!=null   )
				     						where.append(" AND TYT_PERMISO.ID_PERMISO=:idPermiso");							
                   					 
				         if(entity.getIdTipoPermiso()!=null   )
				     						where.append(" AND TYT_PERMISO.ID_TIPO_PERMISO=:idTipoPermiso");							
                   					 
				         if(entity.getNombre()!=null   &&  !entity.getNombre().equals(""))
				     				       where.append(" AND upper(TYT_PERMISO.NOMBRE) like  '%'||upper('%"+entity.getNombre()+"%')||'%'");                   							   
				   					 
				         if(entity.getDescripcion()!=null   &&  !entity.getDescripcion().equals(""))
				     				       where.append(" AND upper(TYT_PERMISO.DESCRIPCION) like  '%'||upper('%"+entity.getDescripcion()+"%')||'%'");                   							   
				   					 
				         if(entity.getUsuarioCrea()!=null   &&  !entity.getUsuarioCrea().equals(""))
				     				       where.append(" AND upper(TYT_PERMISO.USUARIO_CREA) like  '%'||upper('%"+entity.getUsuarioCrea()+"%')||'%'");                   							   
				   					 
				         if(entity.getActivo()!=null   )
				     						where.append(" AND TYT_PERMISO.ACTIVO=:activo");							
                   					 
				         if(entity.getFechaCreacion()!=null   )
				     						where.append(" AND TYT_PERMISO.FECHA_CREACION=:fechaCreacion");							
                   					 
				
		     			     	 objForaneaDTO=new ForaneaDTO();				
	 objForaneaDTO.setCampoForanea("ID_TIPO_PERMISO");				
	 objForaneaDTO.setIdForanea("ID_TIPO_PERMISO");		      
	       	 	                objForaneaDTO.setTabla("TYT_TIPO_PERMISO");		 
   objForaneaDTO.setCampos(""	
	        +"ID_TIPO_PERMISO,"
						          +"NOMBRE,"
						          +"USUARIO_CREA"
						     ); 						 
						 		        				listForaneaDTO.add(objForaneaDTO);
            			     			     			     			     			     		    
		
			return genericDao.findByCriteriaForeign(entity, "TYT_PERMISO",select.toString(),where.toString(),listForaneaDTO,entity.getAliasForaneas());		
		} catch(JaverianaException je) {
			throw je;
		} catch (Exception e) {
        	bitacora.error("ServiceTytPermisoService.findByCriteriaForeign. Causa: " + e.getMessage() + ". Query: " + select.toString(),e);
            throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
		}
	}
	
	
	private void validateName(TytPermisoDTO entity,boolean creating) throws JaverianaException{
	  StringBuilder sql = new StringBuilder("");
	  try{
	  sql.append("select 1 FROM TYT_PERMISO 	where nombre=:nombre");	  
	  sql.append(                   creating?"" :" and ID_PERMISO!=:idPermiso "
				  			      			      			      			      			      			      			     );
	  
	    
		List<TytPermisoDTO> list= genericDao.findByCriteria(entity, sql.toString(),null,false);	
		if(list!=null && list.size()>0)
		   throw javerianaExceptionService.throwException(Parameters.EXCEPCION_NAME, null);
	  
	  } catch(JaverianaException je) {
			throw je;
	  } catch (Exception e) {        	
            throw javerianaExceptionService.throwException(Parameters.EXCEPCION_SERVICE, null);
	  }	
	}
}
