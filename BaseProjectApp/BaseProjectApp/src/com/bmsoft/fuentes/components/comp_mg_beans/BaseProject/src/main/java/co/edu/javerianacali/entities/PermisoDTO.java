package $packageName.entities;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;
import java.util.HashMap;

public class PermisoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	// objeto que contendra el aleas de las respectivas foraneas
	private HashMap<String, String> aliasForaneas;

	private BigDecimal idPermiso;
	private BigDecimal idTipoPermiso;
	private String nombre;
	private String usuarioCrea;
	private String activo;
	private Date fechaCreacion;
	private String descripcion;

	// Objeto que representan las relaciones de la tabla
	private TipoPermisoDTO tipoPermisoDTO;

	public PermisoDTO() {
		tipoPermisoDTO = new TipoPermisoDTO();

		// se llena el objeto con los alias de la tabla
		aliasForaneas = new HashMap<>();
		aliasForaneas.put("TIPO_PERMISO", "T0");
	}

	public HashMap<String, String> getAliasForaneas() {
		return aliasForaneas;
	}

	// Metodos Get y Set de las variables de la clase

	public void setIdPermiso(BigDecimal idPermiso) {
		this.idPermiso = idPermiso;
	}

	public BigDecimal getIdPermiso() {
		return idPermiso;
	}

	public void setIdTipoPermiso(BigDecimal idTipoPermiso) {
		this.idTipoPermiso = idTipoPermiso;
	}

	public BigDecimal getIdTipoPermiso() {
		return idTipoPermiso;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public String getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getActivo() {
		return activo;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	// metodos get de los objeto foraneos
	public TipoPermisoDTO getTipoPermisoDTO() {
		return tipoPermisoDTO;
	}

	// metodos Get y Set de los atributos de los objetos foraneos

	public void setT0IdTipoPermiso(BigDecimal t0IdTipoPermiso) {
		tipoPermisoDTO.setIdTipoPermiso(t0IdTipoPermiso);
	}

	public void setT0Nombre(String t0Nombre) {
		tipoPermisoDTO.setNombre(t0Nombre);
	}

	public void setT0UsuarioCrea(String t0UsuarioCrea) {
		tipoPermisoDTO.setUsuarioCrea(t0UsuarioCrea);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
