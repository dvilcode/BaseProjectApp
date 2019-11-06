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
import co.edu.javerianacali.entities.TytTipoParametroDTO;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import co.edu.javerianacali.interfaces.services.ITytTipoParametroService;
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
 * Clase TytTipoParametroController
 * @author bmsoftGenerator
 * @since V1.0
 */
@ManagedBean(name="TytTipoParametroController")
@ViewScoped
public class TytTipoParametroController implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final Logger bitacora = Logger.getRootLogger();

	@ManagedProperty(value = "#{TytTipoParametroService}")
	private transient ITytTipoParametroService objTytTipoParametroService;
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
	private TytTipoParametroDTO objTytTipoParametroSelected;
	/**
	 * Listado de registros obtenidos desde la BD
	 */
	private List<TytTipoParametroDTO> listEntitiesDTO;
	/**
	 * Objeto filtro para la busqueda
	 */
	private TytTipoParametroDTO objTytTipoParametroFilter;
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
		bitacora.error("TytTipoParametroController.init. Causa: " + e.getMessage());
        mensajes.mostrarMensaje(javerianaExceptionService.throwException(Parameters.EXCEPCION_CONTROLLER, null));
    }
}
	
	/**
	 * Metodo para limpiar atributos de la clase
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void cleanData(){
		objTytTipoParametroFilter = new TytTipoParametroDTO();
		objTytTipoParametroSelected = new TytTipoParametroDTO();
		listEntitiesDTO = new ArrayList<TytTipoParametroDTO>();
		creating=true;
    }
	
	/**
	 * Metodo para limpiar el objeto a guardaro o editar
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void cleanObject(){
		objTytTipoParametroSelected = new TytTipoParametroDTO();
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
						 listEntitiesDTO = objTytTipoParametroService.findByCriteria(objTytTipoParametroFilter,null,false);
						
			if(listEntitiesDTO == null || listEntitiesDTO.size() == 0){
				mensajes.mostrarMensajeBundle(MensajesBean.MENSAJE_ALERTA, Parameters.MENSAJE_TABLA_SIN_REGISTROS);
				return;
			}
		} catch(JaverianaException e){
			mensajes.mostrarMensaje(e);
		} catch (Exception e) {
			bitacora.error("TytTipoParametroController.actionSearch. Causa: " + e.getMessage(),e);
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
			bitacora.error("TytTipoParametroController.actionSave. Causa: " + e.getMessage(),e);
            mensajes.mostrarMensaje(javerianaExceptionService.throwException(Parameters.EXCEPCION_CONTROLLER, null));
			
        }
	}

	
	/**
	 * Evento de crear un nuevo registro
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void actionCreate() throws Exception{
	                                                objTytTipoParametroSelected.setUsuarioCrea(datosSesionBean.getUserId());	
                             objTytTipoParametroSelected.setActivo("S");	
                             objTytTipoParametroSelected.setFechaCreacion(new Date());	
              			objTytTipoParametroService.create(objTytTipoParametroSelected);
			objTytTipoParametroSelected = new TytTipoParametroDTO();
			actionSearch();
			
			mensajes.mostrarMensajeBundle(MensajesBean.MENSAJE_OK, Parameters.MENSAJE_REGISTRO_GUARDADO);
	}
	
	/**
	 * Evento de editar un registro
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void actionEdit() throws Exception{
			if(objTytTipoParametroSelected!=null){
			                                                                                                                                                                    				objTytTipoParametroService.update(objTytTipoParametroSelected);
				objTytTipoParametroSelected = new TytTipoParametroDTO();
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
    public void toggleEnable(TytTipoParametroDTO object) {
        try {
        	if(object.getActivo().equals(Parameters.ACTIVO)){
        		object.setActivo(Parameters.INACTIVO);
            } else {
            	object.setActivo(Parameters.ACTIVO);
            }
        	objTytTipoParametroService.update(object);
        } catch(JaverianaException e){
			mensajes.mostrarMensaje(e);
			actionSearch();
		} catch (Exception e) {
			bitacora.error("TytTipoParametroController.toggleEnable. Causa: " + e.getMessage(),e);
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
    public void loadEdit(TytTipoParametroDTO object) {
    	try {
    		creating = false;
			objTytTipoParametroSelected = object;
			
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
	
	public List<TytTipoParametroDTO> getListEntitiesDTO() {
		return listEntitiesDTO;
	}

	public void setListEntitiesDTO(List<TytTipoParametroDTO> listEntitiesDTO) {
		this.listEntitiesDTO = listEntitiesDTO;
	}
	
	public void setObjTytTipoParametroSelected(TytTipoParametroDTO objTytTipoParametroSelected){
		this.objTytTipoParametroSelected=objTytTipoParametroSelected;
	}
	
	public TytTipoParametroDTO getObjTytTipoParametroSelected(){
		return objTytTipoParametroSelected;
	}
	
	public void setObjTytTipoParametroFilter(TytTipoParametroDTO objTytTipoParametroFilter){
		this.objTytTipoParametroFilter=objTytTipoParametroFilter;
	}
	
	public TytTipoParametroDTO getObjTytTipoParametroFilter(){
		return objTytTipoParametroFilter;
	}
	
	public void setMensajes(MensajesBean mensajes) {
		this.mensajes = mensajes;
	}
		
	public void setObjTytTipoParametroService(ITytTipoParametroService objTytTipoParametroService) {
		this.objTytTipoParametroService = objTytTipoParametroService;
	}

	public void setJaverianaExceptionService(
			IJaverianaExceptionService javerianaExceptionService) {
		this.javerianaExceptionService = javerianaExceptionService;
	}
	
	public void setDatosSesionBean(DatosSesionBean datosSesionBean) {
		this.datosSesionBean = datosSesionBean;
	}
	
	
		
}