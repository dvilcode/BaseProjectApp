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
import co.edu.javerianacali.entities.TytPermisoDTO;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import co.edu.javerianacali.interfaces.services.ITytPermisoService;
import co.edu.javerianacali.interfaces.services.ITytTipoPermisoService;
import co.edu.javerianacali.entities.TytTipoPermisoDTO;
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
 * Clase TytPermisoController
 * @author bmsoftGenerator
 * @since V1.0
 */
@ManagedBean(name="TytPermisoController")
@ViewScoped
public class TytPermisoController implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final Logger bitacora = Logger.getRootLogger();

	@ManagedProperty(value = "#{TytPermisoService}")
	private transient ITytPermisoService objTytPermisoService;
	@ManagedProperty(value = "#{JaverianaExceptionService}")
	private transient IJaverianaExceptionService javerianaExceptionService;
	@ManagedProperty(value = "#{mensajesBean}")
	private MensajesBean mensajes;
	@ManagedProperty(value = "#{DatosSesionBean}")
	private DatosSesionBean datosSesionBean;
	
	
    @ManagedProperty(value = "#{TytTipoPermisoService}")
	private transient ITytTipoPermisoService objTytTipoPermisoService;
	private List<SelectItem>  itemsTytTipoPermiso;

	private List<SelectItem> itemsActivo;
	/**
	 * Objeto para creacion/edicion
	 */
	private TytPermisoDTO objTytPermisoSelected;
	/**
	 * Listado de registros obtenidos desde la BD
	 */
	private List<TytPermisoDTO> listEntitiesDTO;
	/**
	 * Objeto filtro para la busqueda
	 */
	private TytPermisoDTO objTytPermisoFilter;
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
		bitacora.error("TytPermisoController.init. Causa: " + e.getMessage());
        mensajes.mostrarMensaje(javerianaExceptionService.throwException(Parameters.EXCEPCION_CONTROLLER, null));
    }
}
	
	/**
	 * Metodo para limpiar atributos de la clase
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void cleanData(){
		objTytPermisoFilter = new TytPermisoDTO();
		objTytPermisoSelected = new TytPermisoDTO();
		listEntitiesDTO = new ArrayList<TytPermisoDTO>();
		creating=true;
    }
	
	/**
	 * Metodo para limpiar el objeto a guardaro o editar
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void cleanObject(){
		objTytPermisoSelected = new TytPermisoDTO();
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
		
				itemsTytTipoPermiso=new ArrayList<SelectItem>();
	    List<TytTipoPermisoDTO> listTytTipoPermiso=objTytTipoPermisoService.findByCriteria(new TytTipoPermisoDTO(),null,false);
	    if(listTytTipoPermiso!=null && listTytTipoPermiso.size()>0){
		   for(TytTipoPermisoDTO obj:listTytTipoPermiso){
			   SelectItem selectItem=new SelectItem();
			   selectItem.setValue(obj.getIdTipoPermiso());
			   selectItem.setLabel(obj.getNombre().toString());
			   itemsTytTipoPermiso.add(selectItem);
		    }
		}
        		
		
	}
	
	/**
	 * Evento de buscar
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void actionSearch(){
		try {
						listEntitiesDTO = objTytPermisoService.findByCriteriaForeign(objTytPermisoFilter);			
						
			if(listEntitiesDTO == null || listEntitiesDTO.size() == 0){
				mensajes.mostrarMensajeBundle(MensajesBean.MENSAJE_ALERTA, Parameters.MENSAJE_TABLA_SIN_REGISTROS);
				return;
			}
		} catch(JaverianaException e){
			mensajes.mostrarMensaje(e);
		} catch (Exception e) {
			bitacora.error("TytPermisoController.actionSearch. Causa: " + e.getMessage(),e);
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
			bitacora.error("TytPermisoController.actionSave. Causa: " + e.getMessage(),e);
            mensajes.mostrarMensaje(javerianaExceptionService.throwException(Parameters.EXCEPCION_CONTROLLER, null));
			
        }
	}

	
	/**
	 * Evento de crear un nuevo registro
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void actionCreate() throws Exception{
	                                                                              objTytPermisoSelected.setUsuarioCrea(datosSesionBean.getUserId());	
                             objTytPermisoSelected.setActivo("S");	
                             objTytPermisoSelected.setFechaCreacion(new Date());	
              			objTytPermisoService.create(objTytPermisoSelected);
			objTytPermisoSelected = new TytPermisoDTO();
			actionSearch();
			
			mensajes.mostrarMensajeBundle(MensajesBean.MENSAJE_OK, Parameters.MENSAJE_REGISTRO_GUARDADO);
	}
	
	/**
	 * Evento de editar un registro
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void actionEdit() throws Exception{
			if(objTytPermisoSelected!=null){
			                                                                                                                                                                                                                                    				objTytPermisoService.update(objTytPermisoSelected);
				objTytPermisoSelected = new TytPermisoDTO();
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
    public void toggleEnable(TytPermisoDTO object) {
        try {
        	if(object.getActivo().equals(Parameters.ACTIVO)){
        		object.setActivo(Parameters.INACTIVO);
            } else {
            	object.setActivo(Parameters.ACTIVO);
            }
        	objTytPermisoService.update(object);
        } catch(JaverianaException e){
			mensajes.mostrarMensaje(e);
			actionSearch();
		} catch (Exception e) {
			bitacora.error("TytPermisoController.toggleEnable. Causa: " + e.getMessage(),e);
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
    public void loadEdit(TytPermisoDTO object) {
    	try {
    		creating = false;
			objTytPermisoSelected = object;
			
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
	
	public List<TytPermisoDTO> getListEntitiesDTO() {
		return listEntitiesDTO;
	}

	public void setListEntitiesDTO(List<TytPermisoDTO> listEntitiesDTO) {
		this.listEntitiesDTO = listEntitiesDTO;
	}
	
	public void setObjTytPermisoSelected(TytPermisoDTO objTytPermisoSelected){
		this.objTytPermisoSelected=objTytPermisoSelected;
	}
	
	public TytPermisoDTO getObjTytPermisoSelected(){
		return objTytPermisoSelected;
	}
	
	public void setObjTytPermisoFilter(TytPermisoDTO objTytPermisoFilter){
		this.objTytPermisoFilter=objTytPermisoFilter;
	}
	
	public TytPermisoDTO getObjTytPermisoFilter(){
		return objTytPermisoFilter;
	}
	
	public void setMensajes(MensajesBean mensajes) {
		this.mensajes = mensajes;
	}
		
	public void setObjTytPermisoService(ITytPermisoService objTytPermisoService) {
		this.objTytPermisoService = objTytPermisoService;
	}

	public void setJaverianaExceptionService(
			IJaverianaExceptionService javerianaExceptionService) {
		this.javerianaExceptionService = javerianaExceptionService;
	}
	
	public void setDatosSesionBean(DatosSesionBean datosSesionBean) {
		this.datosSesionBean = datosSesionBean;
	}
	
	
	
	public void setObjTytTipoPermisoService(ITytTipoPermisoService objTytTipoPermisoService) {
        this.objTytTipoPermisoService = objTytTipoPermisoService;
    }
	
	public void setItemsTytTipoPermiso(List<SelectItem>  itemsTytTipoPermiso) {
        this.itemsTytTipoPermiso = itemsTytTipoPermiso;
    }
	
	public List<SelectItem> getItemsTytTipoPermiso(){
		return itemsTytTipoPermiso;
	}
     	
}