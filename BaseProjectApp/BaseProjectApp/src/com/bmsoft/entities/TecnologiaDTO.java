package com.bmsoft.entities;

import java.util.List;

public class TecnologiaDTO {

	private String nombre;
	private List<String> dependencias;
	private List<String> carpetasAdicionales;
	private boolean isSelected;
	
	//Accessors
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<String> getDependencias() {
		return dependencias;
	}
	public void setDependencias(List<String> dependencias) {
		this.dependencias = dependencias;
	}
	public List<String> getCarpetasAdicionales() {
		return carpetasAdicionales;
	}
	public void setCarpetasAdicionales(List<String> carpetasAdicionales) {
		this.carpetasAdicionales = carpetasAdicionales;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
}
