package $packageName.entities;

import java.io.Serializable;

public class ForaneaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	public ForaneaDTO() {
		super();
	}

	public ForaneaDTO(String tabla, String campoForanea, String idForanea, String campos) {
		super();
		this.tabla = tabla;
		this.campoForanea = campoForanea;
		this.idForanea = idForanea;
		this.campos = campos;
	}

	// NOMBRE DE LA TABLA FORANEA
	private String tabla;
	// CAMPO FORANEA EN TABLA PADRE
	private String campoForanea;
	// CAMPO FORANEA EN TABLA FORANEA, SOLO SE ASIGNA SI EL NOMBRE DEL CAMPO ES
	// DIFERENTE
	private String idForanea;
	// CAMPOS QUE SE SELECCIONARAN EN LA FORANEA
	private String campos;

	public String getTabla() {
		return tabla;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public String getCampoForanea() {
		return campoForanea;
	}

	public void setCampoForanea(String campoForanea) {
		this.campoForanea = campoForanea;
	}

	public String getIdForanea() {
		return idForanea;
	}

	public void setIdForanea(String idForanea) {
		this.idForanea = idForanea;
	}

	public String getCampos() {
		return campos;
	}

	public void setCampos(String campos) {
		this.campos = campos;
	}

}
