package $packageName.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

public class PermisosRolDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public PermisosRolDTO() {
		permisoDTO = new PermisoDTO();
		rolesAplicacionDTO = new RolesAplicacionDTO();

		// se llena el objeto con los alias de la tabla
		aliasForaneas = new HashMap<>();
		aliasForaneas.put("PERMISO", "T0");
		aliasForaneas.put("ROLES_APLICACION", "T1");
	}

	// objeto que contendra el aleas de las respectivas foraneas
	private HashMap<String, String> aliasForaneas;

	private BigDecimal idPermisoRol;
	private BigDecimal idPermiso;
	private Long idRol;
	private String valor;
	private String usuarioCrea;
	private Date fechaCreacion;
	private String nombreRol;
	private String descripcionRol;
	private Boolean isSelected;
	private String activo;

	// Objeto que representan las relaciones de la tabla

	private PermisoDTO permisoDTO;
	private RolesAplicacionDTO rolesAplicacionDTO;

	public HashMap<String, String> getAliasForaneas() {
		return aliasForaneas;
	}

	// Metodos Get y Set de las variables de la clase

	public void setIdPermisoRol(BigDecimal idPermisoRol) {
		this.idPermisoRol = idPermisoRol;
	}

	public BigDecimal getIdPermisoRol() {
		return idPermisoRol;
	}

	public void setIdPermiso(BigDecimal idPermiso) {
		this.idPermiso = idPermiso;
	}

	public BigDecimal getIdPermiso() {
		return idPermiso;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public String getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	// metodos get de los objeto foraneos
	public PermisoDTO getPermisoDTO() {
		return permisoDTO;
	}

	public RolesAplicacionDTO getRolesAplicacionDTO() {
		return rolesAplicacionDTO;
	}

	// metodos Get y Set de los atributos de los objetos foraneos

	public void setT0IdPermiso(BigDecimal t0IdPermiso) {
		permisoDTO.setIdPermiso(t0IdPermiso);
	}

	public void setT0IdTipoPermiso(BigDecimal t0IdTipoPermiso) {
		permisoDTO.setIdTipoPermiso(t0IdTipoPermiso);
	}

	public void setT0Nombre(String t0Nombre) {
		permisoDTO.setNombre(t0Nombre);
	}

	public void setT0UsuarioCrea(String t0UsuarioCrea) {
		permisoDTO.setUsuarioCrea(t0UsuarioCrea);
	}

	public void setT1IdRol(Long t1IdRol) {
		rolesAplicacionDTO.setIdRol(t1IdRol);
	}

	public void setT1Nombre(String t1Nombre) {
		rolesAplicacionDTO.setNombre(t1Nombre);
	}

	public void setT1RolesPeople(String t1RolesPeople) {
		rolesAplicacionDTO.setRolesPeople(t1RolesPeople);
	}

	public void setT1UsuarioCrea(String t1UsuarioCrea) {
		rolesAplicacionDTO.setUsuarioCrea(t1UsuarioCrea);
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public String getDescripcionRol() {
		return descripcionRol;
	}

	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public void setAliasForaneas(HashMap<String, String> aliasForaneas) {
		this.aliasForaneas = aliasForaneas;
	}

	public void setPermisoDTO(PermisoDTO permisoDTO) {
		this.permisoDTO = permisoDTO;
	}

	public void setRolesAplicacionDTO(RolesAplicacionDTO rolesAplicacionDTO) {
		this.rolesAplicacionDTO = rolesAplicacionDTO;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

}
