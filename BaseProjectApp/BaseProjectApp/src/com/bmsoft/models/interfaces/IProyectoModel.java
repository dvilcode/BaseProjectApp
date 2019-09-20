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
	 * @param templatesList
	 */
	public void runGenerateComponent( HashMap<String, String> varList, List<String> pathList, JsonObject textCodesJson, String urlDestino, List<String> templatesList, String pathLoader) throws Exception;
	
}
