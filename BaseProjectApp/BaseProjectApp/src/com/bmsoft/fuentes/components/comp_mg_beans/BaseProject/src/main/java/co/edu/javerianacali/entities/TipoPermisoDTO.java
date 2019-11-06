package $packageName.entities;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;
import java.util.HashMap;

public class TipoPermisoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	// objeto que contendra el aleas de las respectivas foraneas
	private HashMap<String, String> aliasForaneas;

	private BigDecimal idTipoPermiso;
	private String nombre;
	private String usuarioCrea;
	private String activo;
	private Date fechaCreacion;

	// Objeto que representan las relaciones de la tabla

	public TipoPermisoDTO() {

		// se llena el objeto con los alias de la tabla
		aliasForaneas = new HashMap<>();
	}

	public HashMap<String, String> getAliasForaneas() {
		return aliasForaneas;
	}

	// Metodos Get y Set de las variables de la clase

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

	// metodos Get y Set de los atributos de los objetos foraneos

}
