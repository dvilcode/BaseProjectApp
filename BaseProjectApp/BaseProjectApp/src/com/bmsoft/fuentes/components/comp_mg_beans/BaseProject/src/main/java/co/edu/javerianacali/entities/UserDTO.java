package $packageName.entities;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger bitacora = Logger.getRootLogger();

	private String oprid;
	private String emplid;
	private String documentoIdentidad;
	private String nombres;
	private String apellidos;
	private String cargo;
	private String name;
	private String pass;
	private String idEmpresa;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getOprid() {
		return oprid;
	}

	public void setOprid(String oprid) {
		this.oprid = oprid;
	}

	public String getEmplid() {
		return emplid;
	}

	public void setEmplid(String emplid) {
		this.emplid = emplid;
	}

	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

}
