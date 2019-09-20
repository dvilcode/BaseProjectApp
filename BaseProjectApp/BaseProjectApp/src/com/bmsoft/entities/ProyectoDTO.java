package com.bmsoft.entities;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Document;

public class ProyectoDTO {
	
	/**
	 * Variables relacionadas a la creacion de 
	 * proyecto.
	 * @author Dv
	 */
	private String nombre;
	private String contexto;
	private String empaquetado;
	private List<TecnologiaDTO> tecnologias;
	private Document pom;
	private String groupId;
	private String artifactId;
	private String descripcion;
	private String destino;
	private String tipoProyecto;
	private List<FuenteDTO> fuentes;
	private Document archivoConfiguracion;	
	
	public ProyectoDTO() {
		tecnologias = new ArrayList<TecnologiaDTO>();
		fuentes = new ArrayList<FuenteDTO>();
	}
	
	//Accessors.
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String  nombre) {
		this.nombre = nombre;
	}
	public String nombreProperty(String  nombre) {
		return this.nombre;
	}
	public String getContexto() {
		return contexto;
	}
	public void setContexto(String contexto) {
		this.contexto = contexto;
	}
	public String getEmpaquetado() {
		return empaquetado;
	}
	public void setEmpaquetado(String empaquetado) {
		this.empaquetado = empaquetado;
	}
	public List<TecnologiaDTO> getTecnologias() {
		return tecnologias;
	}
	public void setTecnologias(List<TecnologiaDTO> tecnologias) {
		this.tecnologias = tecnologias;
	}
	public Document getPom() {
		return pom;
	}
	public void setPom(Document pom) {
		this.pom = pom;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public List<FuenteDTO> getFuentes() {
		return fuentes;
	}
	public void setFuentes(List<FuenteDTO> fuentes) {
		this.fuentes = fuentes;
	}
	public String getTipoProyecto() {
		return tipoProyecto;
	}
	public void setTipoProyecto(String tipoProyecto) {
		this.tipoProyecto = tipoProyecto;
	}
	public Document getArchivoConfiguracion() {
		return archivoConfiguracion;
	}
	public void setArchivoConfiguracion(Document archivoConfiguracion) {
		this.archivoConfiguracion = archivoConfiguracion;
	}
}
