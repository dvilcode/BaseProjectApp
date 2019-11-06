package $packageName.managedbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import $packageName.entities.RolesAplicacionDTO;

/**
 * Clase ParametroController
 * 
 * @author bmsoftGenerator
 * @since V1.0
 */
@ManagedBean(name = "DatosSesionBean")
@SessionScoped
public class DatosSesionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userId;
	private String emplId;
	private String portal;
	private Long idUsuarioExterno;
	private String emailUsuarioExterno;
	private String idCentro;
	
	//Objeto que persiste la cita seleccionada al autenticar en el dialog.
	private Long idCita;
	
	// Roles usuario
	private List<RolesAplicacionDTO> rolesList;
	private Long idRolActual;
	private HashMap<String, String> valoresNotificacion;

	// SelecItems de los roles que se muestran en la barra de menu
	private List<SelectItem> itemRoles;
	
	//Objeto que valida la autenticacion desde el dialog.
	private boolean isAutenticado;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmplId() {
		return emplId;

	}

	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}

	public String getPortal() {
		return portal;
	}

	public void setPortal(String portal) {
		this.portal = portal;
	}

	public List<RolesAplicacionDTO> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<RolesAplicacionDTO> rolesList) {
		this.rolesList = rolesList;
	}

	public Long getIdRolActual() {
		return idRolActual;
	}

	public void setIdRolActual(Long idRolActual) {
		this.idRolActual = idRolActual;
	}

	public List<SelectItem> getItemRoles() {
		return itemRoles;
	}

	public void setItemRoles(List<SelectItem> itemRoles) {
		this.itemRoles = itemRoles;
	}

	public String getNombreRolActual() {
		if (rolesList != null && rolesList.size() > 0) {
			for (RolesAplicacionDTO rol : rolesList) {
				if (rol.getIdRol().equals(idRolActual))
					return rol.getNombre();
			}
		}

		return null;
	}

	public Long getIdUsuarioExterno() {
		return idUsuarioExterno;
	}

	public String getEmailUsuarioExterno() {
		return emailUsuarioExterno;
	}

	public String getIdCentro() {
		return idCentro;
	}

	public void setIdUsuarioExterno(Long idUsuarioExterno) {
		this.idUsuarioExterno = idUsuarioExterno;
	}

	public void setEmailUsuarioExterno(String emailUsuarioExterno) {
		this.emailUsuarioExterno = emailUsuarioExterno;
	}

	public void setIdCentro(String idCentro) {
		this.idCentro = idCentro;
	}

	public HashMap<String, String> getValoresNotificacion() {
		return valoresNotificacion;
	}

	public void setValoresNotificacion(HashMap<String, String> valoresNotificacion) {
		this.valoresNotificacion = valoresNotificacion;
	}

	public boolean getIsAutenticado() {
		return isAutenticado;
	}

	public void setIsAutenticado(boolean isAutenticado) {
		this.isAutenticado = isAutenticado;
	}

	public Long getIdCita() {
		return idCita;
	}

	public void setIdCita(Long idCita) {
		this.idCita = idCita;
	}

	
	
	
}
