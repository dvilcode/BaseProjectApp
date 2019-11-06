package $packageName.entities;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;
import java.util.HashMap;

public class TytPermisosRolDTO implements Serializable{

	private static final long serialVersionUID = 1L;
    //objeto que contendra el aleas de las respectivas foraneas
  	private HashMap<String, String> aliasForaneas;
	
	private Long idPermisoRol;
	private Long idPermiso;
	private Long idRol;
	private String valor;
	private String usuarioCrea;
	private Date fechaCreacion;

    //Objeto que representan las relaciones de la tabla
  	private TytPermisoDTO  tytPermisoDTO;	
  	private TytRolesAplicacionDTO  tytRolesAplicacionDTO;	
  
  public TytPermisosRolDTO(){
	   
	
	//se llena el objeto con los alias de la tabla 
	aliasForaneas=new HashMap<>();			
    	aliasForaneas.put("TYT_PERMISO", "T0");
    	aliasForaneas.put("TYT_ROLES_APLICACION", "T1");
    	}

  public HashMap<String, String> getAliasForaneas() {
		return aliasForaneas;
	}
  
//metodos de instanciacion 

//metodos get  de los objeto foraneos 

 	private void intanceTytPermisoDTO() {
		if(tytPermisoDTO==null)
		   tytPermisoDTO=new TytPermisoDTO();		
	}
  	private void intanceTytRolesAplicacionDTO() {
		if(tytRolesAplicacionDTO==null)
		   tytRolesAplicacionDTO=new TytRolesAplicacionDTO();		
	}
    
  
//Metodos Get y Set de las variables de la clase

	public void setIdPermisoRol(Long idPermisoRol) {
		this.idPermisoRol = idPermisoRol;
	}
	
	public Long getIdPermisoRol() {
		return idPermisoRol;
	}
	public void setIdPermiso(Long idPermiso) {
		this.idPermiso = idPermiso;
	}
	
	public Long getIdPermiso() {
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


//metodos get  de los objeto foraneos 
 	public TytPermisoDTO getTytPermisoDTO() {
		return tytPermisoDTO;
	}
	
	public void setTytPermisoDTO (TytPermisoDTO tytPermisoDTO) {
		this.tytPermisoDTO=tytPermisoDTO;
	}
  	public TytRolesAplicacionDTO getTytRolesAplicacionDTO() {
		return tytRolesAplicacionDTO;
	}
	
	public void setTytRolesAplicacionDTO (TytRolesAplicacionDTO tytRolesAplicacionDTO) {
		this.tytRolesAplicacionDTO=tytRolesAplicacionDTO;
	}
  
//metodos Get y Set de los atributos de los objetos foraneos      

     
      			public void setT0IdPermiso(Long t0IdPermiso) {
		  intanceTytPermisoDTO();
		 tytPermisoDTO.setIdPermiso(t0IdPermiso);
		}
		    
				public void setT0IdTipoPermiso(Long t0IdTipoPermiso) {
		  intanceTytPermisoDTO();
		 tytPermisoDTO.setIdTipoPermiso(t0IdTipoPermiso);
		}
		    
				public void setT0Nombre(String t0Nombre) {
		  intanceTytPermisoDTO();
		 tytPermisoDTO.setNombre(t0Nombre);
		}
		    
				public void setT0Descripcion(String t0Descripcion) {
		  intanceTytPermisoDTO();
		 tytPermisoDTO.setDescripcion(t0Descripcion);
		}
		    
				public void setT0UsuarioCrea(String t0UsuarioCrea) {
		  intanceTytPermisoDTO();
		 tytPermisoDTO.setUsuarioCrea(t0UsuarioCrea);
		}
		    
	      			public void setT1IdRol(Long t1IdRol) {
		  intanceTytRolesAplicacionDTO();
		 tytRolesAplicacionDTO.setIdRol(t1IdRol);
		}
		    
				public void setT1Nombre(String t1Nombre) {
		  intanceTytRolesAplicacionDTO();
		 tytRolesAplicacionDTO.setNombre(t1Nombre);
		}
		    
				public void setT1RolesPeople(String t1RolesPeople) {
		  intanceTytRolesAplicacionDTO();
		 tytRolesAplicacionDTO.setRolesPeople(t1RolesPeople);
		}
		    
				public void setT1UsuarioCrea(String t1UsuarioCrea) {
		  intanceTytRolesAplicacionDTO();
		 tytRolesAplicacionDTO.setUsuarioCrea(t1UsuarioCrea);
		}
		    
	  


}