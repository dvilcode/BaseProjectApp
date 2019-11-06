package $packageName.entities;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;
import java.util.HashMap;

public class TytRolesAplicacionDTO implements Serializable{

	private static final long serialVersionUID = 1L;
    //objeto que contendra el aleas de las respectivas foraneas
  	private HashMap<String, String> aliasForaneas;
	
	private Long idRol;
	private String nombre;
	private String rolesPeople;
	private String usuarioCrea;
	private String activo;
	private Date fechaCreacion;

    //Objeto que representan las relaciones de la tabla
  
  public TytRolesAplicacionDTO(){
	   
	
	//se llena el objeto con los alias de la tabla 
	aliasForaneas=new HashMap<>();			
    	}

  public HashMap<String, String> getAliasForaneas() {
		return aliasForaneas;
	}
  
//metodos de instanciacion 

//metodos get  de los objeto foraneos 

   
  
//Metodos Get y Set de las variables de la clase

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	
	public Long getIdRol() {
		return idRol;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setRolesPeople(String rolesPeople) {
		this.rolesPeople = rolesPeople;
	}
	
	public String getRolesPeople() {
		return rolesPeople;
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


//metodos get  de los objeto foraneos 
 
//metodos Get y Set de los atributos de los objetos foraneos      

     
  


}