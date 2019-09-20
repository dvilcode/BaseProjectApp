package com.bmsoft.utils;

public class Parameters {

	public static final String PATH_PROYECTO_BASE = "proyecto/src/com/bmsoft/proyectoBase";
	public static final String PATH_LOGO_EMPRESA = "src/com/bmsoft/views/images/bmsoftware.jpg";
	
	/**
	 * Enum para tipo de archivos.
	 * @author Dv
	 */
	public enum Tipo_Archivo{
		CONFIGURACION(".conf");
		
		private String value;
		
		private Tipo_Archivo(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	/**
	 * Enum para los signos del archivo de configuracion
	 * a tener en cuenta en las tecnologias.
	 * @author Dv
	 */
	public enum Configuracion{
		NOMBRE("<name>"), DEPENDENCIA("<dependency>"), CARPETAS_ADICIONALES("<addFolder>"),NAME_PROJECT("<projectName>");
		
		private String value;
		
		private Configuracion(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	/**
	 * Enum para tipo proyecto
	 * @author Dv
	 */
	public enum Tipo_Proyecto {
		JAVERIANA("puj"), FENALCO("fen"), BMSOFTWARE("bms");

		private String value;

		private Tipo_Proyecto(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
}
