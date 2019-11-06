package $packageName.managedbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import $packageName.entities.ParametroDTO;
import $packageName.interfaces.services.IJaverianaExceptionService;
import $packageName.interfaces.services.IParametroService;

/**
 * Clase ParametroController
 * 
 * @author bmsoftGenerator
 * @since V1.0
 */
@ManagedBean(name = "ParametroBean")
@SessionScoped
public class ParametroBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger bitacora = Logger.getRootLogger();

	@ManagedProperty(value = "#{ParametroService}")
	private transient IParametroService objParametroService;
	/**
	 * Objeto para consulta
	 */
	private ParametroDTO parametroSelected;
	private String timeZone = "GMT-5";
	/**
	 * Listado de registros obtenidos desde la BD
	 */
	private List<ParametroDTO> listEntitiesDTO;
	@ManagedProperty(value = "#{JaverianaExceptionService}")
	private transient IJaverianaExceptionService javerianaExceptionService;

	/**
	 * Obtener el valor de un parametro por su id
	 * 
	 * @author bmsoftGenerator
	 * @since V1.0
	 */
	public String getParametro(int idParametro) {
		try {

			ParametroDTO entity = new ParametroDTO();
			entity.setIdParametro(new Long(idParametro + ""));
			ParametroDTO entityReturn = objParametroService.findByPK(entity);

			return entityReturn.getValor();
		} catch (Exception e) {
			bitacora.error("ParametroController.actionSearch. Causa: " + e.getMessage());
			return "";
		}
	}

	public List<ParametroDTO> getListEntitiesDTO() {
		return listEntitiesDTO;
	}

	public void setListEntitiesDTO(List<ParametroDTO> listEntitiesDTO) {
		this.listEntitiesDTO = listEntitiesDTO;
	}

	public void setParametroSelected(ParametroDTO parametroSelected) {
		this.parametroSelected = parametroSelected;
	}

	public ParametroDTO getParametroSelected() {
		return parametroSelected;
	}

	public void setObjParametroService(IParametroService objParametroService) {
		this.objParametroService = objParametroService;
	}

	public void setJaverianaExceptionService(IJaverianaExceptionService javerianaExceptionService) {
		this.javerianaExceptionService = javerianaExceptionService;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

}
