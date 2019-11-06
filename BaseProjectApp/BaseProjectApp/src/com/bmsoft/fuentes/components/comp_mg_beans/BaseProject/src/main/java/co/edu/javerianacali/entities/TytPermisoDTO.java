package $packageName.entities;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;
import java.util.HashMap;

public class TytPermisoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
    //objeto que contendra el aleas de las respectivas foraneas
  	private HashMap<String, String> aliasForaneas;
	
	private Long idPermiso;
	private Long idTipoPermiso;
	private String nombre;
	private String descripcion;
	private String usuarioCrea;
	private String activo;
	private Date fechaCreacion;

    //Objeto que representan las relaciones de la tabla
  	private TytTipoPermisoDTO  tytTipoPermisoDTO;	
  
  public TytPermisoDTO(){
	   
	
	//se llena el objeto con los alias de la tabla 
	aliasForaneas=new HashMap<>();			
    	aliasForaneas.put("TYT_TIPO_PERMISO", "T0");
    	}

  public HashMap<String, String> getAliasForaneas() {
		return aliasForaneas;
	}
  
//metodos de instanciacion 

//metodos get  de los objeto foraneos 

 	private void intanceTytTipoPermisoDTO() {
		if(tytTipoPermisoDTO==null)
		   tytTipoPermisoDTO=new TytTipoPermisoDTO();		
	}
    
  
//Metodos Get y Set de las variables de la clase

	public void setIdPermiso(Long idPermiso) {
		this.idPermiso = idPermiso;
	}
	
	public Long getIdPermiso() {
		return idPermiso;
	}
	public void setIdTipoPermiso(Long idTipoPermiso) {
		this.idTipoPermiso = idTipoPermiso;
	}
	
	public Long getIdTipoPermiso() {
		return idTipoPermiso;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
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
 	public TytTipoPermisoDTO getTytTipoPermisoDTO() {
		return tytTipoPermisoDTO;
	}
	
	public void setTytTipoPermisoDTO (TytTipoPermisoDTO tytTipoPermisoDTO) {
		this.tytTipoPermisoDTO=tytTipoPermisoDTO;
	}
  
//metodos Get y Set de los atributos de los objetos foraneos      

     
      			public void setT0IdTipoPermiso(Long t0IdTipoPermiso) {
		  intanceTytTipoPermisoDTO();
		 tytTipoPermisoDTO.setIdTipoPermiso(t0IdTipoPermiso);
		}
		    
				public void setT0Nombre(String t0Nombre) {
		  intanceTytTipoPermisoDTO();
		 tytTipoPermisoDTO.setNombre(t0Nombre);
		}
		    
				public void setT0UsuarioCrea(String t0UsuarioCrea) {
		  intanceTytTipoPermisoDTO();
		 tytTipoPermisoDTO.setUsuarioCrea(t0UsuarioCrea);
		}
		    
	  


}