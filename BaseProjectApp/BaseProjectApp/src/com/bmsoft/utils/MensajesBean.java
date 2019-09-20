package com.bmsoft.utils;

import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MensajesBean {

	public final Alert.AlertType ERROR = AlertType.ERROR;
	public final Alert.AlertType CONFIRMACION = AlertType.CONFIRMATION;
	public final Alert.AlertType INFORMACION = AlertType.INFORMATION;
	public final Alert.AlertType ALERTA = AlertType.WARNING;
	
	/**
	 * Muestra mensaje FX en la pantalla.
	 * @param labelContent
	 * @param type
	 * @param argMsg TODO
	 * @throws Exception
	 * @author Dv
	 */
	public void mostrarMensajeBundleFx(String labelContent, AlertType type, String argMsg)throws Exception{
		ResourceBundle bundle = null;
		Alert alert = null;
		
		try {
			alert = new Alert(type);
			bundle = ResourceBundle.getBundle("com.bmsoft.views.i18n.labels");
			alert.setTitle(getTitle(type));
			alert.setHeaderText(null);
			alert.setContentText(bundle.getString(labelContent) + (argMsg != null ? "\n" + argMsg : ""));
			
			alert.showAndWait();
			
		}catch(Exception e) {
			System.out.print("MensajesBean.mostrarMensajeFx. Causa: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Configura el title del mensaje dependiendo del tipo de alerta.
	 * @param type
	 * @return
	 * @throws Exception
	 * @author Dv
	 */
	private String getTitle(AlertType type)throws Exception{
		String header = "";
		
		if(type.compareTo(ERROR)==0) {
			header = "Error";
					
		}else if(type.compareTo(CONFIRMACION)==0) {
			header = "Confirmacion";
					
		}else if(type.compareTo(INFORMACION)==0) {
			header = "Informacion";
					
		}else if(type.compareTo(ALERTA)==0) {
			header = "Alerta";
					
		} 
		return header;
	}
}
