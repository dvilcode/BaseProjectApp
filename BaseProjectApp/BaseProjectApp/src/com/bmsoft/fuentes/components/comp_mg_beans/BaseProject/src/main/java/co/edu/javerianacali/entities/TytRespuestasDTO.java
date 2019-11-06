package $packageName.entities;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;
import java.util.HashMap;

public class TytRespuestasDTO implements Serializable{

	private static final long serialVersionUID = 1L;
    //objeto que contendra el aleas de las respectivas foraneas
  	private HashMap<String, String> aliasForaneas;
	
	private Long idRespuesta;
	private Long idPregunta;
	private Long idEncuestaServicio;
	private String respuesta;

    //Objeto que representan las relaciones de la tabla
  	private TytPreguntasEncuestaDTO  tytPreguntasEncuestaDTO;	
  	private TytEncuestaServicioDTO  tytEncuestaServicioDTO;	
  
  public TytRespuestasDTO(){
	   
	
	//se llena el objeto con los alias de la tabla 
	aliasForaneas=new HashMap<>();			
    	aliasForaneas.put("TYT_PREGUNTAS_ENCUESTA", "T0");
    	aliasForaneas.put("TYT_ENCUESTA_SERVICIO", "T1");
    	}

  public HashMap<String, String> getAliasForaneas() {
		return aliasForaneas;
	}
  
//metodos de instanciacion 

//metodos get  de los objeto foraneos 

 	private void intanceTytPreguntasEncuestaDTO() {
		if(tytPreguntasEncuestaDTO==null)
		   tytPreguntasEncuestaDTO=new TytPreguntasEncuestaDTO();		
	}
  	private void intanceTytEncuestaServicioDTO() {
		if(tytEncuestaServicioDTO==null)
		   tytEncuestaServicioDTO=new TytEncuestaServicioDTO();		
	}
    
  
//Metodos Get y Set de las variables de la clase

	public void setIdRespuesta(Long idRespuesta) {
		this.idRespuesta = idRespuesta;
	}
	
	public Long getIdRespuesta() {
		return idRespuesta;
	}
	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
	}
	
	public Long getIdPregunta() {
		return idPregunta;
	}
	
	public void setIdEncuestaServicio(Long idEncuestaServicio) {
		this.idEncuestaServicio = idEncuestaServicio;
	}
	
	public Long getIdEncuestaServicio() {
		return idEncuestaServicio;
	}
	
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	public String getRespuesta() {
		return respuesta;
	}


//metodos get  de los objeto foraneos 
 	public TytPreguntasEncuestaDTO getTytPreguntasEncuestaDTO() {
		return tytPreguntasEncuestaDTO;
	}
	
	public void setTytPreguntasEncuestaDTO (TytPreguntasEncuestaDTO tytPreguntasEncuestaDTO) {
		this.tytPreguntasEncuestaDTO=tytPreguntasEncuestaDTO;
	}
  	public TytEncuestaServicioDTO getTytEncuestaServicioDTO() {
		return tytEncuestaServicioDTO;
	}
	
	public void setTytEncuestaServicioDTO (TytEncuestaServicioDTO tytEncuestaServicioDTO) {
		this.tytEncuestaServicioDTO=tytEncuestaServicioDTO;
	}
  
//metodos Get y Set de los atributos de los objetos foraneos      

     
      			public void setT0IdPregunta(Long t0IdPregunta) {
		  intanceTytPreguntasEncuestaDTO();
		 tytPreguntasEncuestaDTO.setIdPregunta(t0IdPregunta);
		}
		    
				public void setT0IdEncuesta(Long t0IdEncuesta) {
		  intanceTytPreguntasEncuestaDTO();
		 tytPreguntasEncuestaDTO.setIdEncuesta(t0IdEncuesta);
		}
		    
				public void setT0Pregunta(String t0Pregunta) {
		  intanceTytPreguntasEncuestaDTO();
		 tytPreguntasEncuestaDTO.setPregunta(t0Pregunta);
		}
		    
				public void setT0Ayuda(String t0Ayuda) {
		  intanceTytPreguntasEncuestaDTO();
		 tytPreguntasEncuestaDTO.setAyuda(t0Ayuda);
		}
		    
				public void setT0Orden(Long t0Orden) {
		  intanceTytPreguntasEncuestaDTO();
		 tytPreguntasEncuestaDTO.setOrden(t0Orden);
		}
		    
	      			public void setT1IdEncuestaServicio(Long t1IdEncuestaServicio) {
		  intanceTytEncuestaServicioDTO();
		 tytEncuestaServicioDTO.setIdEncuestaServicio(t1IdEncuestaServicio);
		}
		    
				public void setT1IdServicioSolicitud(Long t1IdServicioSolicitud) {
		  intanceTytEncuestaServicioDTO();
		 tytEncuestaServicioDTO.setIdServicioSolicitud(t1IdServicioSolicitud);
		}
		    
				public void setT1IdEncuesta(Long t1IdEncuesta) {
		  intanceTytEncuestaServicioDTO();
		 tytEncuestaServicioDTO.setIdEncuesta(t1IdEncuesta);
		}
		    
				public void setT1Comentarios(String t1Comentarios) {
		  intanceTytEncuestaServicioDTO();
		 tytEncuestaServicioDTO.setComentarios(t1Comentarios);
		}
		    
				public void setT1UsuarioCrea(String t1UsuarioCrea) {
		  intanceTytEncuestaServicioDTO();
		 tytEncuestaServicioDTO.setUsuarioCrea(t1UsuarioCrea);
		}
		    
				public void setT1FechaCreacion(Date t1FechaCreacion) {
		  intanceTytEncuestaServicioDTO();
		 tytEncuestaServicioDTO.setFechaCreacion(t1FechaCreacion);
		}
		    
	  


}