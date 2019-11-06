package $packageName.managedbeans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import $packageName.exception.JaverianaException;
import $packageName.interfaces.services.IJaverianaExceptionService;
import $packageName.interfaces.services.IPermisosRolService;
import $packageName.utils.Parameters;

/**
 * Clase ParametroController
 * 
 * @author bmsoftGenerator
 * @since V1.0
 */
@ManagedBean(name = "DatosAutorizacionBean")
@SessionScoped
public class DatosAutorizacionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger bitacora = Logger.getRootLogger();
	@ManagedProperty(value = "#{DatosSesionBean}")
	private DatosSesionBean datoSession;
	@ManagedProperty(value = "#{JaverianaExceptionService}")
	private transient IJaverianaExceptionService javerianaExceptionService;
	@ManagedProperty(value = "#{PermisosRolService}")
	private transient IPermisosRolService objPermisosRolService;
	
	@PostConstruct
	public void init() {
		System.out.println("Pruebas");
	}

	/**
	 * Metodo para validar permisos de ejecucion
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 */
	public boolean validarPermiso(String idPermiso) throws JaverianaException {
		try {
			String identificador = datoSession.getIdRolActual() + "-" + idPermiso;
			Boolean permiso = (Boolean) (objPermisosRolService.getPermiso(Parameters.CACHE_PERMISOS, identificador));
			return permiso != null ? permiso : false;
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("DatosAutorizacion.validarPermiso Causa: " + e.getMessage(),e);
		}

		return false;
	}

	/**
	 * Metodo para obtener valor de un permiso
	 * 
	 * @author Yesid Murillo
	 * @since V1.0
	 */
	public String getValorPermiso(String idPermiso) throws JaverianaException {
		try {
			String identificador = datoSession.getIdRolActual() + "-" + idPermiso;
			String consulta = (String) (objPermisosRolService.getPermiso(Parameters.CACHE_PERMISOS, identificador));
			if (consulta == null) {
				String[] valores = new String[1];
				valores[0] = identificador;
				throw javerianaExceptionService.throwException("siac002", valores);
			}
			return consulta;
		} catch (JaverianaException je) {
			throw je;
		} catch (Exception e) {
			bitacora.error("DatosAutorizacion.getValorPermiso Causa: " + e.getMessage(),e);
			throw javerianaExceptionService.throwException("base03", null);
		}
	}

	public void setDatoSession(DatosSesionBean datoSession) {
		this.datoSession = datoSession;
	}

	public void setJaverianaExceptionService(IJaverianaExceptionService javerianaExceptionService) {
		this.javerianaExceptionService = javerianaExceptionService;
	}

	public void setObjPermisosRolService(IPermisosRolService objPermisosRolService) {
		this.objPermisosRolService = objPermisosRolService;
	}

}
