package com.bmsoft.models.interfaces;

import com.bmsoft.models.model.ProyectoModel;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;

public interface IProyectoModel {

	/**
	 * Ejecuta la logica para generar
	 * el componente seleccionado por el usuario.
	 * @author Dv
	 */
	public void runGenerateComponent( HashMap<String, String> varList, List<String> pathList, JsonObject textCodesJson, String urlDestino) throws Exception;
	
}
