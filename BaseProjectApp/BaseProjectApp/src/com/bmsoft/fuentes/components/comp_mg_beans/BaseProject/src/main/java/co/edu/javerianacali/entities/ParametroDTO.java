package $packageName.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

public class ParametroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public ParametroDTO() {
		tipoParametroDTO = new TipoParametroDTO();

		// se llena el objeto con los alias de la tabla
		aliasForaneas = new HashMap<>();
		aliasForaneas.put("TIPO_PARAMETRO", "T0");
	}

	// objeto que contendra el aleas de las respectivas foraneas
	private HashMap<String, String> aliasForaneas;

	private Long idParametro;
	private BigDecimal idTipoParametro;
	private String nombre;
	private String descripcion;
	private String valor;
	private String usuarioCrea;
	private Date fechaCreacion;

	// Objeto que representan las relaciones de la tabla

	private TipoParametroDTO tipoParametroDTO;

	public HashMap<String, String> getAliasForaneas() {
		return aliasForaneas;
	}

	// Metodos Get y Set de las variables de la clase

	public void setIdParametro(Long idParametro) {
		this.idParametro = idParametro;
	}

	public Long getIdParametro() {
		return idParametro;
	}

	public void setIdTipoParametro(BigDecimal idTipoParametro) {
		this.idTipoParametro = idTipoParametro;
	}

	public BigDecimal getIdTipoParametro() {
		return idTipoParametro;
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
	public TipoParametroDTO getTipoParametroDTO() {
		return tipoParametroDTO;
	}

	// metodos Get y Set de los atributos de los objetos foraneos

	public void setT0IdTipoParametro(BigDecimal t0IdTipoParametro) {
		tipoParametroDTO.setIdTipoParametro(t0IdTipoParametro);
	}

	public void setT0Nombre(String t0Nombre) {
		tipoParametroDTO.setNombre(t0Nombre);
	}

	public void setT0UsuarioCrea(String t0UsuarioCrea) {
		tipoParametroDTO.setUsuarioCrea(t0UsuarioCrea);
	}

}
