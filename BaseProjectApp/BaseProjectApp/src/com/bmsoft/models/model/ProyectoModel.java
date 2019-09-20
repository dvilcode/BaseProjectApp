package com.bmsoft.models.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.bmsoft.controllers.ProyectoController;
import com.bmsoft.models.interfaces.IProyectoModel;
import com.google.gson.JsonObject;

public class ProyectoModel implements IProyectoModel{
	
	private static final String PARAMETER_SIGNAL_DOT = ".";
	private static final String PARAMETER_SIGNAL_DIRECTORY = "/";
	private static final String PARAMETER_SIGNAL_DIRECTORY_WINDOWS = "\\";
	

	@Override
	public void runGenerateComponent(HashMap<String, String> varList, List<String> pathList, JsonObject textCodesJson, String urlDestino, List<String> templatesList , String pathLoader)throws Exception {
		File newDirectory = null;
		boolean isCreated = false;
		boolean isPermission = false;
		boolean isExist = false;
		List<String> newPathList = new ArrayList<>();
		
		try {
			
			//	Reemplaza los valores de variables si existen en el path.
			newPathList = replaceVariablesIntoPath( varList, pathList, textCodesJson );
			
			//Se recorre los path formateados para la generacion de archivos.
			for( String path : newPathList ) {
				
				
				String signalFile = getSignalRoot( textCodesJson, ProyectoController.PARAMETER_TEXT_CODES_FIELD_FILE );
				String[] directory = path.split( signalFile );
				
				//Posicion 0 - Path de carpetas | Posicion 1 - Archivo.
				String newPath = directory[0].replace( PARAMETER_SIGNAL_DIRECTORY, PARAMETER_SIGNAL_DIRECTORY_WINDOWS );
				System.out.println( "New Path: " + urlDestino + newPath );
				newDirectory = new File( urlDestino + newPath );
				
//				isPermission = newDirectory.canWrite();
//				if( isPermission == false ) {
//					Exception permited = new Exception( "No tiene los permisos necesarios para crear en: " + urlDestino );
//					throw permited;
//				}
				
				isExist = newDirectory.exists();
				
				isCreated = newDirectory.mkdirs();
				if( isExist == false && isCreated == false ) {
					Exception created = new Exception( "No se pudo crear el directorio: " + newPath );
					throw created;
				}
				
				// Valida la generacion del archivo.
				if( directory.length > 1 ) { //Si el array es mayor a 1 indica que configuraron un archivo.
				
					
					//Se guarda las propiedades del archivo a generar.
					String[] objFile = directory[1].split( "\\" + PARAMETER_SIGNAL_DOT ); // Position 0 - Nombre | Position 1 - Extension 
					
					//Se inicializa el motor de velocity.
					VelocityEngine engine = new VelocityEngine();
					Properties props = new Properties();
					System.out.println(pathLoader);
				    props.put("file.resource.loader.path", pathLoader.replace( PARAMETER_SIGNAL_DIRECTORY_WINDOWS, PARAMETER_SIGNAL_DIRECTORY) );
				    engine.init(props);
					
					//Se Inicializa el contexto de velocity con las variables a reemplazar.
					VelocityContext context = new VelocityContext();
					for( Map.Entry<String, String> variable : varList.entrySet() ) {
						
						String signalVariable = getSignalRoot( textCodesJson, ProyectoController.PARAMETER_TEXT_CODES_FIELD_VARIABLE );
						String newKey = variable.getKey().replace( signalVariable, "");
						
						context.put( newKey, variable.getValue() );
					}
					
					//Se genera el archivo asociado al directorio.
					generateFileToVelocity( newDirectory.getAbsolutePath(), engine, context, objFile, templatesList);
				}
				
					
			}
			
			
		}catch( Exception e ) {
			System.out.println( "ProyectoModel.runGenerateComponent.causa: " + e.getMessage() );
			e.printStackTrace();
			
			throw e;
		}
		
	}
	
	/**
	 * Se encarga de generar el archivo en la ruta especificada
	 * con las plantillas de velocity.
	 * @param urlPathCreated
	 * @param file
	 * @param varList
	 * @param textCodesJson
	 * @throws Exception
	 * @author Dv
	 */
	public void generateFileToVelocity(String urlPathCreated, VelocityEngine engine, VelocityContext context, String[] objFile, List<String> templatesList)throws Exception{
		
		//Se recupera la plantilla de la ruta configurada.
		for( String template : templatesList) {
			
			
			//Debe tener el mismo nombre la plantilla con el path del archivo de configuracion para hacer el match.
			if( template.contains( objFile[ 0 ] ) == true ) {
				
				Template velTemplate = engine.getTemplate( objFile[ 0 ] + PARAMETER_SIGNAL_DOT + "vm" );
				StringWriter strWriter = new StringWriter();
				velTemplate.merge( context, strWriter);
				
				String urlFile = urlPathCreated + PARAMETER_SIGNAL_DIRECTORY_WINDOWS + objFile[ 0 ] + PARAMETER_SIGNAL_DOT + objFile[ 1 ] ;
				File file = new File( urlFile);
				file.createNewFile();
				
				FileOutputStream output = new FileOutputStream( urlFile );
				output.write( strWriter.toString().getBytes() );
				output.flush();
				output.close();
			
				break;
			}
			
		}
		
	}
	
	/**
	 * Reemplaza las variables asignadas en el path (Si las tiene)
	 * por los valores correspondientes ingresados.
	 * @param varList
	 * @param pathList
	 * @throws Exception
	 * @author Dv
	 */
	private List<String> replaceVariablesIntoPath( HashMap<String, String> varList, List<String> pathList, JsonObject textCodesJson )throws Exception{
		
		List<String> list = new ArrayList<String>();
		list.addAll( pathList );
		
		String signalRoot = getSignalRoot( textCodesJson, ProyectoController.PARAMETER_TEXT_CODES_FIELD_ROOT );
		
		for(int i=0; i<pathList.size(); i++) {
			
			
			for( Map.Entry<String, String> variable : varList.entrySet() ) {
				
				if( pathList.get( i ).contains( variable.getKey() ) == true ) {
					
					String value = variable.getValue().replace( PARAMETER_SIGNAL_DOT, PARAMETER_SIGNAL_DIRECTORY );
					String newPath = pathList.get( i ).replace( variable.getKey() , value ).replace( signalRoot, "");
					list.set( i , newPath );
					
					break;
				}
			}
		}
		
		return list;
	}

	/**
	 * Retorna la señal ingresada de los codigos
	 * de texto.
	 * @param textCodesJson
	 * @param parameter
	 * @return
	 * @throws Exception
	 * @author Dv
	 */
	private String getSignalRoot( JsonObject textCodesJson , String parameter)throws Exception{
		String signal = "";
		
		signal = textCodesJson.get( parameter ).getAsString();
		
		return signal;
	}
	
}
