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
import co.edu.javerianacali.entities.TytPermisosRolDTO;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import co.edu.javerianacali.interfaces.services.ITytPermisosRolService;
import co.edu.javerianacali.interfaces.services.ITytPermisoService;
import co.edu.javerianacali.entities.TytPermisoDTO;
import co.edu.javerianacali.interfaces.services.ITytRolesAplicacionService;
import co.edu.javerianacali.entities.TytRolesAplicacionDTO;
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
 * Clase TytPermisosRolController
 * @author bmsoftGenerator
 * @since V1.0
 */
@ManagedBean(name="TytPermisosRolController")
@ViewScoped
public class TytPermisosRolController implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final Logger bitacora = Logger.getRootLogger();

	@ManagedProperty(value = "#{TytPermisosRolService}")
	private transient ITytPermisosRolService objTytPermisosRolService;
	@ManagedProperty(value = "#{JaverianaExceptionService}")
	private transient IJaverianaExceptionService javerianaExceptionService;
	@ManagedProperty(value = "#{mensajesBean}")
	private MensajesBean mensajes;
	@ManagedProperty(value = "#{DatosSesionBean}")
	private DatosSesionBean datosSesionBean;
	
	
    @ManagedProperty(value = "#{TytPermisoService}")
	private transient ITytPermisoService objTytPermisoService;
	private List<SelectItem>  itemsTytPermiso;
    @ManagedProperty(value = "#{TytRolesAplicacionService}")
	private transient ITytRolesAplicacionService objTytRolesAplicacionService;
	private List<SelectItem>  itemsTytRolesAplicacion;

	private List<SelectItem> itemsActivo;
	/**
	 * Objeto para creacion/edicion
	 */
	private TytPermisosRolDTO objTytPermisosRolSelected;
	/**
	 * Listado de registros obtenidos desde la BD
	 */
	private List<TytPermisosRolDTO> listEntitiesDTO;
	/**
	 * Objeto filtro para la busqueda
	 */
	private TytPermisosRolDTO objTytPermisosRolFilter;
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
		bitacora.error("TytPermisosRolController.init. Causa: " + e.getMessage());
        mensajes.mostrarMensaje(javerianaExceptionService.throwException(Parameters.EXCEPCION_CONTROLLER, null));
    }
}
	
	/**
	 * Metodo para limpiar atributos de la clase
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void cleanData(){
		objTytPermisosRolFilter = new TytPermisosRolDTO();
		objTytPermisosRolSelected = new TytPermisosRolDTO();
		listEntitiesDTO = new ArrayList<TytPermisosRolDTO>();
		creating=true;
    }
	
	/**
	 * Metodo para limpiar el objeto a guardaro o editar
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void cleanObject(){
		objTytPermisosRolSelected = new TytPermisosRolDTO();
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
		
				itemsTytPermiso=new ArrayList<SelectItem>();
	    List<TytPermisoDTO> listTytPermiso=objTytPermisoService.findByCriteria(new TytPermisoDTO(),null,false);
	    if(listTytPermiso!=null && listTytPermiso.size()>0){
		   for(TytPermisoDTO obj:listTytPermiso){
			   SelectItem selectItem=new SelectItem();
			   selectItem.setValue(obj.getIdPermiso());
			   selectItem.setLabel(obj.getNombre().toString());
			   itemsTytPermiso.add(selectItem);
		    }
		}
        		itemsTytRolesAplicacion=new ArrayList<SelectItem>();
	    List<TytRolesAplicacionDTO> listTytRolesAplicacion=objTytRolesAplicacionService.findByCriteria(new TytRolesAplicacionDTO(),null,false);
	    if(listTytRolesAplicacion!=null && listTytRolesAplicacion.size()>0){
		   for(TytRolesAplicacionDTO obj:listTytRolesAplicacion){
			   SelectItem selectItem=new SelectItem();
			   selectItem.setValue(obj.getIdRol());
			   selectItem.setLabel(obj.getNombre().toString());
			   itemsTytRolesAplicacion.add(selectItem);
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
						listEntitiesDTO = objTytPermisosRolService.findByCriteriaForeign(objTytPermisosRolFilter);			
						
			if(listEntitiesDTO == null || listEntitiesDTO.size() == 0){
				mensajes.mostrarMensajeBundle(MensajesBean.MENSAJE_ALERTA, Parameters.MENSAJE_TABLA_SIN_REGISTROS);
				return;
			}
		} catch(JaverianaException e){
			mensajes.mostrarMensaje(e);
		} catch (Exception e) {
			bitacora.error("TytPermisosRolController.actionSearch. Causa: " + e.getMessage(),e);
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
			bitacora.error("TytPermisosRolController.actionSave. Causa: " + e.getMessage(),e);
            mensajes.mostrarMensaje(javerianaExceptionService.throwException(Parameters.EXCEPCION_CONTROLLER, null));
			
        }
	}

	
	/**
	 * Evento de crear un nuevo registro
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void actionCreate() throws Exception{
	                                                                              objTytPermisosRolSelected.setUsuarioCrea(datosSesionBean.getUserId());	
                             objTytPermisosRolSelected.setFechaCreacion(new Date());	
              			objTytPermisosRolService.create(objTytPermisosRolSelected);
			objTytPermisosRolSelected = new TytPermisosRolDTO();
			actionSearch();
			
			mensajes.mostrarMensajeBundle(MensajesBean.MENSAJE_OK, Parameters.MENSAJE_REGISTRO_GUARDADO);
	}
	
	/**
	 * Evento de editar un registro
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void actionEdit() throws Exception{
			if(objTytPermisosRolSelected!=null){
			                                                                                                                                                                                                    				objTytPermisosRolService.update(objTytPermisosRolSelected);
				objTytPermisosRolSelected = new TytPermisosRolDTO();
                 actionSearch();
				mensajes.mostrarMensajeBundle(MensajesBean.MENSAJE_OK, Parameters.MENSAJE_REGISTRO_MODIFICADO);
			}
	}
	
																			    
    
    /**
     * Carga un registro para editarlo
     * @author bmsoftGenerator
	 * @since V1.0
     * @param item el objeto que se va a editar
     */
    public void loadEdit(TytPermisosRolDTO object) {
    	try {
    		creating = false;
			objTytPermisosRolSelected = object;
			
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
	
	public List<TytPermisosRolDTO> getListEntitiesDTO() {
		return listEntitiesDTO;
	}

	public void setListEntitiesDTO(List<TytPermisosRolDTO> listEntitiesDTO) {
		this.listEntitiesDTO = listEntitiesDTO;
	}
	
	public void setObjTytPermisosRolSelected(TytPermisosRolDTO objTytPermisosRolSelected){
		this.objTytPermisosRolSelected=objTytPermisosRolSelected;
	}
	
	public TytPermisosRolDTO getObjTytPermisosRolSelected(){
		return objTytPermisosRolSelected;
	}
	
	public void setObjTytPermisosRolFilter(TytPermisosRolDTO objTytPermisosRolFilter){
		this.objTytPermisosRolFilter=objTytPermisosRolFilter;
	}
	
	public TytPermisosRolDTO getObjTytPermisosRolFilter(){
		return objTytPermisosRolFilter;
	}
	
	public void setMensajes(MensajesBean mensajes) {
		this.mensajes = mensajes;
	}
		
	public void setObjTytPermisosRolService(ITytPermisosRolService objTytPermisosRolService) {
		this.objTytPermisosRolService = objTytPermisosRolService;
	}

	public void setJaverianaExceptionService(
			IJaverianaExceptionService javerianaExceptionService) {
		this.javerianaExceptionService = javerianaExceptionService;
	}
	
	public void setDatosSesionBean(DatosSesionBean datosSesionBean) {
		this.datosSesionBean = datosSesionBean;
	}
	
	
	
	public void setObjTytPermisoService(ITytPermisoService objTytPermisoService) {
        this.objTytPermisoService = objTytPermisoService;
    }
	
	public void setItemsTytPermiso(List<SelectItem>  itemsTytPermiso) {
        this.itemsTytPermiso = itemsTytPermiso;
    }
	
	public List<SelectItem> getItemsTytPermiso(){
		return itemsTytPermiso;
	}
     
	public void setObjTytRolesAplicacionService(ITytRolesAplicacionService objTytRolesAplicacionService) {
        this.objTytRolesAplicacionService = objTytRolesAplicacionService;
    }
	
	public void setItemsTytRolesAplicacion(List<SelectItem>  itemsTytRolesAplicacion) {
        this.itemsTytRolesAplicacion = itemsTytRolesAplicacion;
    }
	
	public List<SelectItem> getItemsTytRolesAplicacion(){
		return itemsTytRolesAplicacion;
	}
     	
}