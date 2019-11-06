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

import co.edu.javerianacali.entities.TytPreferenciasDTO;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Map;

import co.edu.javerianacali.interfaces.services.ITytPreferenciasService;
import co.edu.javerianacali.interfaces.services.ITytEmpresasTransportadoraService;
import co.edu.javerianacali.entities.TytEmpresasTransportadoraDTO;
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
 * Clase TytPreferenciasController
 * @author bmsoftGenerator
 * @since V1.0
 */
@ManagedBean(name="TytPreferenciasController")
@ViewScoped
public class TytPreferenciasController implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final Logger bitacora = Logger.getRootLogger();

	@ManagedProperty(value = "#{TytPreferenciasService}")
	private transient ITytPreferenciasService objTytPreferenciasService;
	@ManagedProperty(value = "#{JaverianaExceptionService}")
	private transient IJaverianaExceptionService javerianaExceptionService;
	@ManagedProperty(value = "#{mensajesBean}")
	private MensajesBean mensajes;
	@ManagedProperty(value = "#{DatosSesionBean}")
	private DatosSesionBean datosSesionBean;
	
	
    @ManagedProperty(value = "#{TytEmpresasTransportadoraService}")
	private transient ITytEmpresasTransportadoraService objTytEmpresasTransportadoraService;
	private List<SelectItem>  itemsTytEmpresasTransportadora;
	
	private List<SelectItem>  itemsCargos;

	private List<SelectItem> itemsActivo;
	/**
	 * Objeto para creacion/edicion
	 */
	private TytPreferenciasDTO objTytPreferenciasSelected;
	/**
	 * Listado de registros obtenidos desde la BD
	 */
	private List<TytPreferenciasDTO> listEntitiesDTO;
	/**
	 * Objeto filtro para la busqueda
	 */
	private TytPreferenciasDTO objTytPreferenciasFilter;
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
		bitacora.error("TytPreferenciasController.init. Causa: " + e.getMessage());
        mensajes.mostrarMensaje(javerianaExceptionService.throwException(Parameters.EXCEPCION_CONTROLLER, null));
    }
}
	
	/**
	 * Metodo para limpiar atributos de la clase
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void cleanData(){
		objTytPreferenciasFilter = new TytPreferenciasDTO();
		objTytPreferenciasSelected = new TytPreferenciasDTO();
		listEntitiesDTO = new ArrayList<TytPreferenciasDTO>();
		creating=true;
    }
	
	/**
	 * Metodo para limpiar el objeto a guardaro o editar
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void cleanObject(){
		objTytPreferenciasSelected = new TytPreferenciasDTO();
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
		
				itemsTytEmpresasTransportadora=new ArrayList<SelectItem>();
	    List<TytEmpresasTransportadoraDTO> listTytEmpresasTransportadora=objTytEmpresasTransportadoraService.findByCriteria(new TytEmpresasTransportadoraDTO(),null,false);
	    if(listTytEmpresasTransportadora!=null && listTytEmpresasTransportadora.size()>0){
		   for(TytEmpresasTransportadoraDTO obj:listTytEmpresasTransportadora){
			   SelectItem selectItem=new SelectItem();
			   selectItem.setValue(obj.getIdEmpresaTransportadora());
			   selectItem.setLabel(obj.getNombre().toString());
			   itemsTytEmpresasTransportadora.add(selectItem);
		    }
		}
        		
		itemsCargos=new ArrayList<SelectItem>();
	    List<Map<String, Object>> listCargos=objTytPreferenciasService.findByCargos();
	    if(listCargos!=null && listCargos.size()>0){
	       for(Map<String, Object> obj:listCargos){
	    	   itemsCargos.add(new SelectItem(obj.get("ID"),obj.get("NOMBRE").toString()));
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
			listEntitiesDTO = objTytPreferenciasService.findByPreferencias();								
			if(listEntitiesDTO == null || listEntitiesDTO.size() == 0){
				mensajes.mostrarMensajeBundle(MensajesBean.MENSAJE_ALERTA, Parameters.MENSAJE_TABLA_SIN_REGISTROS);
				return;
			}
		} catch(JaverianaException e){
			mensajes.mostrarMensaje(e);
		} catch (Exception e) {
			bitacora.error("TytPreferenciasController.actionSearch. Causa: " + e.getMessage(),e);
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
			bitacora.error("TytPreferenciasController.actionSave. Causa: " + e.getMessage(),e);
            mensajes.mostrarMensaje(javerianaExceptionService.throwException(Parameters.EXCEPCION_CONTROLLER, null));
			
        }
	}

	
	/**
	 * Evento de crear un nuevo registro
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void actionCreate() throws Exception{
	                                                                              objTytPreferenciasSelected.setActivo("S");	
                             objTytPreferenciasSelected.setUsuarioCrea(datosSesionBean.getUserId());	
                             objTytPreferenciasSelected.setFechaCreacion(new Date());	
                             objTytPreferenciasSelected.setUsuarioModifica(datosSesionBean.getUserId());	
                             objTytPreferenciasSelected.setFechaUltimaModificacion(new Date());	
              			objTytPreferenciasService.create(objTytPreferenciasSelected);
			objTytPreferenciasSelected = new TytPreferenciasDTO();
			actionSearch();
			
			mensajes.mostrarMensajeBundle(MensajesBean.MENSAJE_OK, Parameters.MENSAJE_REGISTRO_GUARDADO);
	}
	
	/**
	 * Evento de editar un registro
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public void actionEdit() throws Exception{
			if(objTytPreferenciasSelected!=null){
			                                                                                                                                                                                                                                                                    objTytPreferenciasSelected.setUsuarioModifica(datosSesionBean.getUserId());	
                                                                objTytPreferenciasSelected.setFechaUltimaModificacion(new Date());	
                                				objTytPreferenciasService.update(objTytPreferenciasSelected);
				objTytPreferenciasSelected = new TytPreferenciasDTO();
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
    public void toggleEnable(TytPreferenciasDTO object) {
        try {
        	if(object.getActivo().equals(Parameters.ACTIVO)){
        		object.setActivo(Parameters.INACTIVO);
            } else {
            	object.setActivo(Parameters.ACTIVO);
            }
        	objTytPreferenciasService.update(object);
        } catch(JaverianaException e){
			mensajes.mostrarMensaje(e);
			actionSearch();
		} catch (Exception e) {
			bitacora.error("TytPreferenciasController.toggleEnable. Causa: " + e.getMessage(),e);
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
    public void loadEdit(TytPreferenciasDTO object) {
    	try {
    		creating = false;
			objTytPreferenciasSelected = object;
			
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
	
	public List<TytPreferenciasDTO> getListEntitiesDTO() {
		return listEntitiesDTO;
	}

	public void setListEntitiesDTO(List<TytPreferenciasDTO> listEntitiesDTO) {
		this.listEntitiesDTO = listEntitiesDTO;
	}
	
	public void setObjTytPreferenciasSelected(TytPreferenciasDTO objTytPreferenciasSelected){
		this.objTytPreferenciasSelected=objTytPreferenciasSelected;
	}
	
	public TytPreferenciasDTO getObjTytPreferenciasSelected(){
		return objTytPreferenciasSelected;
	}
	
	public void setObjTytPreferenciasFilter(TytPreferenciasDTO objTytPreferenciasFilter){
		this.objTytPreferenciasFilter=objTytPreferenciasFilter;
	}
	
	public TytPreferenciasDTO getObjTytPreferenciasFilter(){
		return objTytPreferenciasFilter;
	}
	
	public void setMensajes(MensajesBean mensajes) {
		this.mensajes = mensajes;
	}
		
	public void setObjTytPreferenciasService(ITytPreferenciasService objTytPreferenciasService) {
		this.objTytPreferenciasService = objTytPreferenciasService;
	}

	public void setJaverianaExceptionService(
			IJaverianaExceptionService javerianaExceptionService) {
		this.javerianaExceptionService = javerianaExceptionService;
	}
	
	public void setDatosSesionBean(DatosSesionBean datosSesionBean) {
		this.datosSesionBean = datosSesionBean;
	}
	
	
	
	public void setObjTytEmpresasTransportadoraService(ITytEmpresasTransportadoraService objTytEmpresasTransportadoraService) {
        this.objTytEmpresasTransportadoraService = objTytEmpresasTransportadoraService;
    }
	
	public void setItemsTytEmpresasTransportadora(List<SelectItem>  itemsTytEmpresasTransportadora) {
        this.itemsTytEmpresasTransportadora = itemsTytEmpresasTransportadora;
    }
	
	public List<SelectItem> getItemsTytEmpresasTransportadora(){
		return itemsTytEmpresasTransportadora;
	}

	public List<SelectItem> getItemsCargos() {
		return itemsCargos;
	}

	public void setItemsCargos(List<SelectItem> itemsCargos) {
		this.itemsCargos = itemsCargos;
	}
     	
	
}