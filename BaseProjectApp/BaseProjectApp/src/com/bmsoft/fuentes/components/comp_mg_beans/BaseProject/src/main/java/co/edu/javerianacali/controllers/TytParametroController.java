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
import co.edu.javerianacali.entities.TytParametroDTO;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import co.edu.javerianacali.interfaces.services.ITytParametroService;
import co.edu.javerianacali.interfaces.services.ITytTipoParametroService;
import co.edu.javerianacali.entities.TytTipoParametroDTO;
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
 * Clase TytParametroController
 * @author bmsoftGenerator
 * @since V1.0
 */
@ManagedBean(name="TytParametroController")
@ViewScoped
public class TytParametroController implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final Logger bitacora = Logger.getRootLogger();

	@ManagedProperty(value = "#{TytParametroService}")
	private transient ITytParametroService objTytParametroService;
	@ManagedProperty(value = "#{JaverianaExceptionService}")
	private transient IJaverianaExceptionService javerianaExceptionService;
	@ManagedProperty(value = "#{mensajesBean}")
	private MensajesBean mensajes;
	@ManagedProperty(value = "#{DatosSesionBean}")
	private DatosSesionBean datosSesionBean;
	
	
    @ManagedProperty(value = "#{TytTipoParametroService}")
	private transient ITytTipoParametroService objTytTipoParametroService;
	private List<SelectItem>  itemsTytTipoParametro;

	private List<SelectItem> itemsActivo;
	/**
	 * Objeto para creacion/edicion
	 */
	private TytParametroDTO objTytParametroSelected;
	/**
	 * Listado de registros obtenidos desde la BD
	 */
	private List<TytParametroDTO> listEntitiesDTO;
	/**
	 * Objeto filtro para la busqueda
	 */
	private TytParametroDTO objTytParametroFilter;
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
		bitacora.error("TytParametroController.init. Causa: " + e.getMessage());
        mensajes.mostrarMensaje(javerianaExceptionService.throwException(Parameters.EXCEPCION_CONTROLLER, null));
    }
}
	
	/**
	 * Metodo para limpiar atributos de la clase
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void cleanData(){
		objTytParametroFilter = new TytParametroDTO();
		objTytParametroSelected = new TytParametroDTO();
		listEntitiesDTO = new ArrayList<TytParametroDTO>();
		creating=true;
    }
	
	/**
	 * Metodo para limpiar el objeto a guardaro o editar
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void cleanObject(){
		objTytParametroSelected = new TytParametroDTO();
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
		
				itemsTytTipoParametro=new ArrayList<SelectItem>();
	    List<TytTipoParametroDTO> listTytTipoParametro=objTytTipoParametroService.findByCriteria(new TytTipoParametroDTO(),null,false);
	    if(listTytTipoParametro!=null && listTytTipoParametro.size()>0){
		   for(TytTipoParametroDTO obj:listTytTipoParametro){
			   SelectItem selectItem=new SelectItem();
			   selectItem.setValue(obj.getIdTipoParametro());
			   selectItem.setLabel(obj.getNombre().toString());
			   itemsTytTipoParametro.add(selectItem);
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
						listEntitiesDTO = objTytParametroService.findByCriteriaForeign(objTytParametroFilter);			
						
			if(listEntitiesDTO == null || listEntitiesDTO.size() == 0){
				mensajes.mostrarMensajeBundle(MensajesBean.MENSAJE_ALERTA, Parameters.MENSAJE_TABLA_SIN_REGISTROS);
				return;
			}
		} catch(JaverianaException e){
			mensajes.mostrarMensaje(e);
		} catch (Exception e) {
			bitacora.error("TytParametroController.actionSearch. Causa: " + e.getMessage(),e);
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
			bitacora.error("TytParametroController.actionSave. Causa: " + e.getMessage(),e);
            mensajes.mostrarMensaje(javerianaExceptionService.throwException(Parameters.EXCEPCION_CONTROLLER, null));
			
        }
	}

	
	/**
	 * Evento de crear un nuevo registro
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void actionCreate() throws Exception{
	                                                                                             objTytParametroSelected.setUsuarioCrea(datosSesionBean.getUserId());	
                             objTytParametroSelected.setFechaCreacion(new Date());	
              			objTytParametroService.create(objTytParametroSelected);
			objTytParametroSelected = new TytParametroDTO();
			actionSearch();
			
			mensajes.mostrarMensajeBundle(MensajesBean.MENSAJE_OK, Parameters.MENSAJE_REGISTRO_GUARDADO);
	}
	
	/**
	 * Evento de editar un registro
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void actionEdit() throws Exception{
			if(objTytParametroSelected!=null){
			                                                                                                                                                                                                                                    				objTytParametroService.update(objTytParametroSelected);
				objTytParametroSelected = new TytParametroDTO();
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
    public void loadEdit(TytParametroDTO object) {
    	try {
    		creating = false;
			objTytParametroSelected = object;
			
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
	
	public List<TytParametroDTO> getListEntitiesDTO() {
		return listEntitiesDTO;
	}

	public void setListEntitiesDTO(List<TytParametroDTO> listEntitiesDTO) {
		this.listEntitiesDTO = listEntitiesDTO;
	}
	
	public void setObjTytParametroSelected(TytParametroDTO objTytParametroSelected){
		this.objTytParametroSelected=objTytParametroSelected;
	}
	
	public TytParametroDTO getObjTytParametroSelected(){
		return objTytParametroSelected;
	}
	
	public void setObjTytParametroFilter(TytParametroDTO objTytParametroFilter){
		this.objTytParametroFilter=objTytParametroFilter;
	}
	
	public TytParametroDTO getObjTytParametroFilter(){
		return objTytParametroFilter;
	}
	
	public void setMensajes(MensajesBean mensajes) {
		this.mensajes = mensajes;
	}
		
	public void setObjTytParametroService(ITytParametroService objTytParametroService) {
		this.objTytParametroService = objTytParametroService;
	}

	public void setJaverianaExceptionService(
			IJaverianaExceptionService javerianaExceptionService) {
		this.javerianaExceptionService = javerianaExceptionService;
	}
	
	public void setDatosSesionBean(DatosSesionBean datosSesionBean) {
		this.datosSesionBean = datosSesionBean;
	}
	
	
	
	public void setObjTytTipoParametroService(ITytTipoParametroService objTytTipoParametroService) {
        this.objTytTipoParametroService = objTytTipoParametroService;
    }
	
	public void setItemsTytTipoParametro(List<SelectItem>  itemsTytTipoParametro) {
        this.itemsTytTipoParametro = itemsTytTipoParametro;
    }
	
	public List<SelectItem> getItemsTytTipoParametro(){
		return itemsTytTipoParametro;
	}
     	
}