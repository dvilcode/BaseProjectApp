package $packageName.entities;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;
import java.util.HashMap;

public class TytPreferenciasDTO implements Serializable{

	private static final long serialVersionUID = 1L;
    //objeto que contendra el aleas de las respectivas foraneas
  	private HashMap<String, String> aliasForaneas;
	
	private Long idPreferencia;
	private Long idEmpresaTransportadora;
	private String codigoCargo;
	private Long cantidadAcompanantes;
	private String activo;
	private String usuarioCrea;
	private Date fechaCreacion;
	private String usuarioModifica;
	private Date fechaUltimaModificacion;
	private String nombreCargo;

    //Objeto que representan las relaciones de la tabla
  	private TytEmpresasTransportadoraDTO  tytEmpresasTransportadoraDTO;	
  
  public TytPreferenciasDTO(){
	   
	
	//se llena el objeto con los alias de la tabla 
	aliasForaneas=new HashMap<>();			
    	aliasForaneas.put("TYT_EMPRESAS_TRANSPORTADORA", "T0");
    	}

  public HashMap<String, String> getAliasForaneas() {
		return aliasForaneas;
	}
  
//metodos de instanciacion 

//metodos get  de los objeto foraneos 

 	private void intanceTytEmpresasTransportadoraDTO() {
		if(tytEmpresasTransportadoraDTO==null)
		   tytEmpresasTransportadoraDTO=new TytEmpresasTransportadoraDTO();		
	}
    
  
//Metodos Get y Set de las variables de la clase

	public void setIdPreferencia(Long idPreferencia) {
		this.idPreferencia = idPreferencia;
	}
	
	public Long getIdPreferencia() {
		return idPreferencia;
	}
	public void setIdEmpresaTransportadora(Long idEmpresaTransportadora) {
		this.idEmpresaTransportadora = idEmpresaTransportadora;
	}
	
	public Long getIdEmpresaTransportadora() {
		return idEmpresaTransportadora;
	}
	
	public void setCodigoCargo(String codigoCargo) {
		this.codigoCargo = codigoCargo;
	}
	
	public String getCodigoCargo() {
		return codigoCargo;
	}
	public void setCantidadAcompanantes(Long cantidadAcompanantes) {
		this.cantidadAcompanantes = cantidadAcompanantes;
	}
	
	public Long getCantidadAcompanantes() {
		return cantidadAcompanantes;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	
	public String getActivo() {
		return activo;
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
	public void setUsuarioModifica(String usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}
	
	public String getUsuarioModifica() {
		return usuarioModifica;
	}
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	
	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}


//metodos get  de los objeto foraneos 
 	public TytEmpresasTransportadoraDTO getTytEmpresasTransportadoraDTO() {
		return tytEmpresasTransportadoraDTO;
	}
	
	public void setTytEmpresasTransportadoraDTO (TytEmpresasTransportadoraDTO tytEmpresasTransportadoraDTO) {
		this.tytEmpresasTransportadoraDTO=tytEmpresasTransportadoraDTO;
	}
  
//metodos Get y Set de los atributos de los objetos foraneos      

     
      			public void setT0IdEmpresaTransportadora(Long t0IdEmpresaTransportadora) {
		  intanceTytEmpresasTransportadoraDTO();
		 tytEmpresasTransportadoraDTO.setIdEmpresaTransportadora(t0IdEmpresaTransportadora);
		}
		    
				public void setT0Nombre(String t0Nombre) {
		  intanceTytEmpresasTransportadoraDTO();
		 tytEmpresasTransportadoraDTO.setNombre(t0Nombre);
		}
		    
				public void setT0Contacto(String t0Contacto) {
		  intanceTytEmpresasTransportadoraDTO();
		 tytEmpresasTransportadoraDTO.setContacto(t0Contacto);
		}
		    
				public void setT0Celular(Long t0Celular) {
		  intanceTytEmpresasTransportadoraDTO();
		 tytEmpresasTransportadoraDTO.setCelular(t0Celular);
		}
		    
				public void setT0CorreoElectronico(String t0CorreoElectronico) {
		  intanceTytEmpresasTransportadoraDTO();
		 tytEmpresasTransportadoraDTO.setCorreoElectronico(t0CorreoElectronico);
		}
		    
				public void setT0CoordinadorTransporte(String t0CoordinadorTransporte) {
		  intanceTytEmpresasTransportadoraDTO();
		 tytEmpresasTransportadoraDTO.setCoordinadorTransporte(t0CoordinadorTransporte);
		}
		    
				public void setT0CorreoCoordinador(String t0CorreoCoordinador) {
		  intanceTytEmpresasTransportadoraDTO();
		 tytEmpresasTransportadoraDTO.setCorreoCoordinador(t0CorreoCoordinador);
		}

				public String getNombreCargo() {
					return nombreCargo;
				}

				public void setNombreCargo(String nombreCargo) {
					this.nombreCargo = nombreCargo;
				}
		    
	  


}