package $packageName.controllers;

import org.primefaces.component.datatable.DataTable;
import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.component.UISelectItems;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.bean.ManagedBean;
import co.edu.javerianacali.managedbeans.DatosSesionBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;
import co.edu.javerianacali.entities.TytRolesAplicacionDTO;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import co.edu.javerianacali.interfaces.services.ITytRolesAplicacionService;
import  co.edu.javerianacali.managedbeans.MensajesBean;
import java.lang.Long;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Byte;
import java.math.BigDecimal;
import org.apache.log4j.Logger;
import java.io.Serializable;
import co.edu.javerianacali.interfaces.services.IJaverianaExceptionService;
import co.edu.javerianacali.exception.JaverianaException;
import co.edu.javerianacali.utils.Parameters;

import javax.faces.bean.ManagedProperty;
import javax.annotation.PostConstruct;

/**
 * Clase TytRolesAplicacionController
 * @author bmsoftGenerator
 * @since V1.0
 */
@ManagedBean(name="TytRolesAplicacionController")
@ViewScoped
public class TytRolesAplicacionController implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final Logger bitacora = Logger.getRootLogger();

	@ManagedProperty(value = "#{TytRolesAplicacionService}")
	private transient ITytRolesAplicacionService objTytRolesAplicacionService;
	@ManagedProperty(value = "#{JaverianaExceptionService}")
	private transient IJaverianaExceptionService javerianaExceptionService;
	@ManagedProperty(value = "#{mensajesBean}")
	private MensajesBean mensajes;
	@ManagedProperty(value = "#{DatosSesionBean}")
	private DatosSesionBean datosSesionBean;
	
	

	private List<SelectItem> itemsActivo;
	/**
	 * Objeto para creacion/edicion
	 */
	private TytRolesAplicacionDTO objTytRolesAplicacionSelected;
	/**
	 * Listado de registros obtenidos desde la BD
	 */
	private List<TytRolesAplicacionDTO> listEntitiesDTO;
	/**
	 * Objeto filtro para la busqueda
	 */
	private TytRolesAplicacionDTO objTytRolesAplicacionFilter;
	/**
	 * Controlar si se esta creando un objeto o se esta editando
	 */
	private Boolean creating = false;

	
	
	@PostConstruct
	public void init(){
	try{
		cleanData();
		loadData();
		actionSearch();
	} catch(JaverianaException e){
		mensajes.mostrarMensaje(e);
	} catch (Exception e) {
		bitacora.error("TytRolesAplicacionController.init. Causa: " + e.getMessage());
        mensajes.mostrarMensaje(javerianaExceptionService.throwException(Parameters.EXCEPCION_CONTROLLER, null));
    }
}
	
	/**
	 * Metodo para limpiar atributos de la clase
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void cleanData(){
		objTytRolesAplicacionFilter = new TytRolesAplicacionDTO();
		objTytRolesAplicacionSelected = new TytRolesAplicacionDTO();
		listEntitiesDTO = new ArrayList<TytRolesAplicacionDTO>();
		creating=true;
    }
	
	/**
	 * Metodo para limpiar el objeto a guardaro o editar
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void cleanObject(){
		objTytRolesAplicacionSelected = new TytRolesAplicacionDTO();
		creating=true;
    }

	/**
	 * Metodo para cargar los datos iniciales
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	private void loadData()throws Exception{
		itemsActivo= new ArrayList<SelectItem>();		
		for(int i=0;i<2;i++){
			SelectItem selectItem=new SelectItem();
			selectItem.setValue(i==0?Parameters.ACTIVO:Parameters.INACTIVO);
			selectItem.setLabel(i==0?"Si":"No");
			itemsActivo.add(selectItem);
		}
		
				
		
	}
	
	/**
	 * Evento de buscar
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void actionSearch(){
		try {
						 listEntitiesDTO = objTytRolesAplicacionService.findByCriteria(objTytRolesAplicacionFilter,null,false);
						
			if(listEntitiesDTO == null || listEntitiesDTO.size() == 0){
				mensajes.mostrarMensajeBundle(MensajesBean.MENSAJE_ALERTA, Parameters.MENSAJE_TABLA_SIN_REGISTROS);
				return;
			}
		} catch(JaverianaException e){
			mensajes.mostrarMensaje(e);
		} catch (Exception e) {
			bitacora.error("TytRolesAplicacionController.actionSearch. Causa: " + e.getMessage(),e);
            mensajes.mostrarMensaje(javerianaExceptionService.throwException(Parameters.EXCEPCION_CONTROLLER, null));
        }
	}
	
	/**
	 * Evento asociado al boton guardar, puede ser para crear o editar un registro
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void actionSave(){
		try {
			if(creating){
				actionCreate();
			}
			else{
				actionEdit();
			}
			
			creating=true;
		} catch(JaverianaException e){		    
			actionSearch();
			mensajes.mostrarMensaje(e);			
		} catch (Exception e) {			
			actionSearch();			
			bitacora.error("TytRolesAplicacionController.actionSave. Causa: " + e.getMessage(),e);
            mensajes.mostrarMensaje(javerianaExceptionService.throwException(Parameters.EXCEPCION_CONTROLLER, null));
			
        }
	}

	
	/**
	 * Evento de crear un nuevo registro
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void actionCreate() throws Exception{
	                                                               objTytRolesAplicacionSelected.setUsuarioCrea(datosSesionBean.getUserId());	
                             objTytRolesAplicacionSelected.setActivo("S");	
                             objTytRolesAplicacionSelected.setFechaCreacion(new Date());	
              			objTytRolesAplicacionService.create(objTytRolesAplicacionSelected);
			objTytRolesAplicacionSelected = new TytRolesAplicacionDTO();
			actionSearch();
			
			mensajes.mostrarMensajeBundle(MensajesBean.MENSAJE_OK, Parameters.MENSAJE_REGISTRO_GUARDADO);
	}
	
	/**
	 * Evento de editar un registro
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void actionEdit() throws Exception{
			if(objTytRolesAplicacionSelected!=null){
			                                                                                                                                                                                                    				objTytRolesAplicacionService.update(objTytRolesAplicacionSelected);
				objTytRolesAplicacionSelected = new TytRolesAplicacionDTO();
                 actionSearch();
				mensajes.mostrarMensajeBundle(MensajesBean.MENSAJE_OK, Parameters.MENSAJE_REGISTRO_MODIFICADO);
			}
	}
	
																/**
     * Metodo para activar/desactivar un registro
     * @author bmsoftGenerator
	 * @since V1.0
     * @param item El objeto que se va a activar/desactivar
     */
    public void toggleEnable(TytRolesAplicacionDTO object) {
        try {
        	if(object.getActivo().equals(Parameters.ACTIVO)){
        		object.setActivo(Parameters.INACTIVO);
            } else {
            	object.setActivo(Parameters.ACTIVO);
            }
        	objTytRolesAplicacionService.update(object);
        } catch(JaverianaException e){
			mensajes.mostrarMensaje(e);
			actionSearch();
		} catch (Exception e) {
			bitacora.error("TytRolesAplicacionController.toggleEnable. Causa: " + e.getMessage(),e);
            mensajes.mostrarMensaje(javerianaExceptionService.throwException(Parameters.EXCEPCION_CONTROLLER, null));
			actionSearch();
        }
    }				    
    
    /**
     * Carga un registro para editarlo
     * @author bmsoftGenerator
	 * @since V1.0
     * @param item el objeto que se va a editar
     */
    public void loadEdit(TytRolesAplicacionDTO object) {
    	try {
    		creating = false;
			objTytRolesAplicacionSelected = object;
			
			RequestContext.getCurrentInstance().reset(":formList:formPanel");
        } catch (Exception e) {
        	mensajes.mostrarMensaje(e);
        }
    }
    
   
	/**
     * Getters and setters
     */
	public Boolean getCreating() {
		return creating;
	}
	
	public List<SelectItem> getItemsActivo() {
		return itemsActivo;
	}
	
	public List<TytRolesAplicacionDTO> getListEntitiesDTO() {
		return listEntitiesDTO;
	}

	public void setListEntitiesDTO(List<TytRolesAplicacionDTO> listEntitiesDTO) {
		this.listEntitiesDTO = listEntitiesDTO;
	}
	
	public void setObjTytRolesAplicacionSelected(TytRolesAplicacionDTO objTytRolesAplicacionSelected){
		this.objTytRolesAplicacionSelected=objTytRolesAplicacionSelected;
	}
	
	public TytRolesAplicacionDTO getObjTytRolesAplicacionSelected(){
		return objTytRolesAplicacionSelected;
	}
	
	public void setObjTytRolesAplicacionFilter(TytRolesAplicacionDTO objTytRolesAplicacionFilter){
		this.objTytRolesAplicacionFilter=objTytRolesAplicacionFilter;
	}
	
	public TytRolesAplicacionDTO getObjTytRolesAplicacionFilter(){
		return objTytRolesAplicacionFilter;
	}
	
	public void setMensajes(MensajesBean mensajes) {
		this.mensajes = mensajes;
	}
		
	public void setObjTytRolesAplicacionService(ITytRolesAplicacionService objTytRolesAplicacionService) {
		this.objTytRolesAplicacionService = objTytRolesAplicacionService;
	}

	public void setJaverianaExceptionService(
			IJaverianaExceptionService javerianaExceptionService) {
		this.javerianaExceptionService = javerianaExceptionService;
	}
	
	public void setDatosSesionBean(DatosSesionBean datosSesionBean) {
		this.datosSesionBean = datosSesionBean;
	}
	
	
		
}