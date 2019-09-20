package com.bmsoft.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.velocity.Template;
import org.apache.velocity.runtime.RuntimeInstance;
import org.apache.velocity.runtime.parser.node.ASTReference;
import org.apache.velocity.runtime.parser.node.ParserVisitor;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.runtime.visitor.BaseVisitor;

import com.bmsoft.entities.ProyectoDTO;
import com.bmsoft.models.interfaces.IProyectoModel;
import com.bmsoft.models.model.ProyectoModel;
import com.bmsoft.utils.StringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.bmsoft.utils.MensajesBean;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

public class ProyectoController implements Initializable{

	/* Ruta donde se encuentra el JSON de configuracion de la aplicacion */
	public static final String URL_DATA= "/src/com/bmsoft/data/data.json";
	
	/* Valores genericos para los datos a mostrar */
	private static final String DEFAULT_VALUE_LIST = "Seleccionar";
	private static final String SIGNAL_LABEL_LIST = "-";
	private static final String PARAMETER_SIGNAL_DIRECTORY = "/";
	private static final String PARAMETER_SIGNAL_DIRECTORY_WINDOWS = "\\";
	private static final String PARAMETER_SIGNAL_VELOCITY_EXTENSION = ".vm";
	
	/* Campos de la estructura del JSON que contiene la informacion */
	private static final String PARAMETER_TIPO_COMPONENTE_JSON = "tipoComponente";
	private static final String PARAMETER_TIPO_COMPONENT = "componente";
	private static final String PARAMETER_TIPO_FIELD_PATH = "path";
	
	private static final String PARAMETER_SUBTIPO_COMPONENTE = "subTipo";
	private static final String PARAMETER_SUBTIPO_FIELD_NAME = "nombre";
	private static final String PARAMETER_SUBTIPO_FIELD_SRC_VM = "srcVM";
	//private static final String PARAMETER_SUBTIPO_FIELD_TEMPLATE_VM = "plantillaVm";
	private static final String PARAMETER_SUBTIPO_FIELD_CONFIG = "config";
	
	public static final String PARAMETER_TEXT_CODES = "textCodes";
	public static final String PARAMETER_TEXT_CODES_FIELD_ROOT = "root";
	public static final String PARAMETER_TEXT_CODES_FIELD_FILE = "file";
	public static final String PARAMETER_TEXT_CODES_FIELD_EXTENSION = "extension";
	public static final String PARAMETER_TEXT_CODES_FIELD_VARIABLE = "variable";
	
	/* Objetos utilizados en el funcionamiento de la aplicacion */
	private ProyectoDTO proyectoSelected;
	private IProyectoModel proyectoModel;
	private MensajesBean mensajes;
	private final ObjectProperty<ProyectoDTO> proyectoFilter = new SimpleObjectProperty<>();
	private List<String> templatesList;
	
	private JsonObject data;
	private JsonObject tipoComponentesJson;
	private JsonObject tipoSelectData;
	private JsonObject subTipoComponentesJson;
	private JsonObject subTipoSelectData;
	private JsonObject textCodesJson;
	
	private ObservableList<String> tipoList;
	private ObservableList<String> subTipoList;
	
	private String tipoComponenteSelected;
	private String subTipoComponenteSelected;
	
	private List<String> variables;
	
	/* FXML Injections. */
	@FXML
	private Button btnChooser;
	
	@FXML
	private TextField inpDestino;
	
	@FXML
	private Button btnGenerar;
	
	@FXML
	private AnchorPane ancPane;
	
	@FXML
	private ComboBox<String> cbxTipo;
	
	@FXML
	private ComboBox<String> cbxSubtipo;
	
	
	/**
	 * Carga los datos iniciales de la aplicacion.
	 * @author Dv
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			cleanData();
			loadData();
		}catch(Exception e) {
			System.out.print("ProyectoController.initialize. Causa: "+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Limpia los objetos seleccionados.
	 * @throws Exception
	 * @author Dv
	 */
	public void cleanData()throws Exception{
		
		proyectoSelected = new ProyectoDTO();
		proyectoModel = new ProyectoModel();
		mensajes = new MensajesBean();
		
//		data = null;
//		tipoComponentes = null;
//		tipoSelectData = null;
//		subTipoComponentes = null;
//		subTipoSelectData = null;
//		tipoComponenteSelected = null;
//		subTipoComponenteSelected = null;
		variables = new ArrayList<>();
		templatesList = new ArrayList<>();
		
		//Limpia todos los elementos generados dinamicamente.
		ancPane.getChildren().clear();
	}
	
	/**
	 * Carga la logica inicial de la aplicacion.
	 * @author Dv
	 * @throws Exception
	 */
	private void loadData()throws Exception{
		FileReader reader = null;
		Gson gson = new Gson();
		JsonElement objJson = null;
		String projectPath = null;
		
		try {
			
			//Se lee el archivo JSON que contiene las configuraciones de la aplicacion.
			projectPath = System.getProperty("user.dir");
			reader = new FileReader( projectPath + URL_DATA);
			objJson = gson.fromJson(reader, JsonElement.class);
			
			//Valida que existan datos de configuracion.
			if(objJson == null) {
				mensajes.mostrarMensajeBundleFx("bpa001", mensajes.ERROR, null);
				return;
			}
			
			data = objJson.getAsJsonObject();
			
			//Se cargan la lista de componentes.
			loadTipoComponente();
			addEventTipoComponente();
			if(cbxSubtipo.getItems() != null ) {
				addEventSubTipoComponente();
			}
			
			//Se guarda la configuracion de los codigos de text para la configuracion de directorios.
			textCodesJson = data.getAsJsonObject( PARAMETER_TEXT_CODES );
			 
		}catch (Exception e) {
			mensajes.mostrarMensajeBundleFx("bpa001", mensajes.ERROR, null);
			System.out.println("ProyectoController.loadData.causa: "+ e.getMessage());
			e.printStackTrace();
		}
		
		finally {
			reader.close();
		}
		
		
	}

	/**
	 * Carga los datos de la lista de tipo componente.
	 * @author Dv
	 */
	private void loadTipoComponente() throws Exception{
		List<String> keysTipoComponente = new ArrayList<>();
		keysTipoComponente.add( DEFAULT_VALUE_LIST );
		
		tipoComponentesJson = data.getAsJsonObject( PARAMETER_TIPO_COMPONENTE_JSON );
		Set<Map.Entry<String, JsonElement>> entries = tipoComponentesJson.entrySet();
		for (Map.Entry<String, JsonElement> e : entries) {
			keysTipoComponente.add(e.getKey());
        }
        
		String[] listTipoComponente = Arrays.copyOf(keysTipoComponente.toArray(), keysTipoComponente.toArray().length, String[].class);
        tipoList = FXCollections.observableArrayList( listTipoComponente );
        cbxTipo.setItems( tipoList );
        cbxTipo.getSelectionModel().selectFirst();
	}
	
	/**
	 * Adiciona evento al listado de tipo componente.
	 * @throws Exception
	 * @author Dv
	 */
	private void addEventTipoComponente()throws Exception{
		cbxTipo.valueProperty().addListener( new ChangeListener<String>() {
			
			@Override
			public void changed( ObservableValue ov, String firstValue, String eventValue) {
				
				try {

					//Guarda los datos importantes del tipo seleccionado.
					tipoComponenteSelected = eventValue;
					tipoSelectData = tipoComponentesJson.getAsJsonObject( tipoComponenteSelected );
					System.out.println( "tipo: "+ tipoSelectData );

					//Carga los datos iniciales de la lista de subTipocommponentes.
					loadSubTipoComponente( eventValue );
					
				}catch(Exception e) {
					System.out.println("ProyectoController.addEventTipoComponente.causa: "+ e.getMessage());
					e.printStackTrace();
				}
			}
		
		} );
	}
	
	/**
	 * Metodo que carga el listado de subtipoComponente.
	 * @param tipoComponente
	 * @throws Exception
	 * @author Dv
	 */
	private void loadSubTipoComponente( String tipoComponente ) throws Exception{
		List<String> keysSubTipoComponente = new ArrayList<>();
		keysSubTipoComponente.add( DEFAULT_VALUE_LIST );
		
		subTipoComponentesJson = tipoComponentesJson.getAsJsonObject( tipoComponente );
		subTipoComponentesJson = subTipoComponentesJson.getAsJsonObject( PARAMETER_SUBTIPO_COMPONENTE );
		Set<Map.Entry<String, JsonElement>> entries = subTipoComponentesJson.entrySet();
		for (Map.Entry<String, JsonElement> e : entries) {
			
			JsonObject jsonName = (JsonObject) e.getValue();
			String name = jsonName.get( PARAMETER_SUBTIPO_FIELD_NAME ).getAsString();
			keysSubTipoComponente.add(e.getKey() + SIGNAL_LABEL_LIST + name);
        }
        
		String[] listSubTipoComponente = Arrays.copyOf(keysSubTipoComponente.toArray(), keysSubTipoComponente.toArray().length, String[].class);
		subTipoList = FXCollections.observableArrayList( listSubTipoComponente );
        cbxSubtipo.setItems( subTipoList );
        cbxSubtipo.getSelectionModel().selectFirst();
	}
	
	/**
	 * Ejecuta el evento de seleccion del subTipoComponente.
	 * @throws Exception
	 * @author Dv
	 */
	private void addEventSubTipoComponente()throws Exception{
		cbxSubtipo.valueProperty().addListener( new ChangeListener<String>() {
			
			@Override
			public void changed( ObservableValue ov, String firstValue, String eventValue) {
				
				String[] subTipoLbl = null;
				
				try {
					
					//Carga los datos iniciales de la lista de subTipocommponentes.
					if( eventValue != null && !eventValue.equals( DEFAULT_VALUE_LIST )) {
						
						subTipoLbl = eventValue.split( SIGNAL_LABEL_LIST );
						subTipoComponenteSelected = subTipoLbl[0];
						
						//Guarda los datos importantes del subTipo seleccionado.
						subTipoSelectData = subTipoComponentesJson.getAsJsonObject( subTipoComponenteSelected );
						System.out.println( "subTipo: "+ subTipoSelectData );
						
						//Carga las variables a mapear en pantalla.
						loadVariablesToMap();
					}
					
				}catch(Exception e) {
					System.out.println("ProyectoController.addEventSubTipoComponente.causa: "+ e.getMessage());
					e.printStackTrace();
					try {
						mensajes.mostrarMensajeBundleFx( "global.label.error.subTipoSelected" , mensajes.ERROR, e.getMessage());
					}catch( Exception d) {
						
					}
				}
			}
		
		} );
	}
	
	/**
	 * Carga las variables a mapear
	 * de las plantillas de velocity en pantalla.
	 * @throws Exception
	 * @author Dv
	 */
	private void loadVariablesToMap() throws Exception {
		String projectPath = null;
		String path = null;
		String plantillaVm = null;
		String sourceVm = null;
		File folderVm = null;
		
		//Se captura el path de los archivos de velocity.
		path = tipoSelectData.get( PARAMETER_TIPO_FIELD_PATH ).getAsString();
		projectPath = System.getProperty("user.dir");
		
		//Valida el tipo de componente para cargar las plantillas.
//		if( !tipoComponenteSelected.equals( PARAMETER_TIPO_COMPONENT ) ){
			
			sourceVm = subTipoSelectData.get( PARAMETER_SUBTIPO_FIELD_SRC_VM ).getAsString();
			String sourceFolderVm = projectPath + path + sourceVm;
			sourceFolderVm = sourceFolderVm.replace( PARAMETER_SIGNAL_DIRECTORY, PARAMETER_SIGNAL_DIRECTORY_WINDOWS );
			
			folderVm = new File( sourceFolderVm );
			
			//Se consulta las plantillas de velocity por su extension [ .vm ].
			File[] files = folderVm.listFiles( new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					
					return name.endsWith( PARAMETER_SIGNAL_VELOCITY_EXTENSION );
				}
			});
			
			//Se recorren los archivos para adicionar el path de cada plantilla.
			for( File plantilla : files) {
				templatesList.add( plantilla.getAbsolutePath() );
			}
			
			//Se consultan las variables de las plantillas para adicionar dinamicamente;
			for( String template : templatesList) {
				
				//Se lee la plantilla de velocity.
				Reader velTemplate = new FileReader( template );
				
				//Retorna las variables asociadas a la plantilla.
				readVariablesVelocity( velTemplate, variables );
				
				velTemplate.close();
			}
			
			//Se carga dinamicamente las variables en campos en el formulario.
			addFieldsDynamic( variables );
			
//		}
//		else {
//		
//			//Se captura la plantillaVm a mapear.
//			plantillaVm = subTipoSelectData.get( PARAMETER_SUBTIPO_FIELD_TEMPLATE_VM ).getAsString();
//			
//			//Se lee la plantilla de velocity.
//			Reader velTemplate = new FileReader( projectPath + path + plantillaVm );
//			
//			//Retorna las variables asociadas a la plantilla.
//			readVariablesVelocity( velTemplate, variables );
//			
//			velTemplate.close();
//			
//			//Se carga dinamicamente las variables en campos en el formulario.
//			addFieldsDynamic( variables );
//		}
		
	}
	
	/**
	 * Adiciona las nuevas instancias al panel del formulario
	 * de las variables.
	 * @param variables
	 * @throws Exception
	 * @author Dv
	 */
	private void addFieldsDynamic(List<String> variables) throws Exception{
		
		double layoutX = 14.0;
		double layoutY = 15.0;
		int column = 0;
		
		for(String variable : variables) {
			
			//Se crean las nuevas instancias de las variables.
			Label lblNew = new Label( variable );
			lblNew.setLayoutX( layoutX );
			lblNew.setLayoutY( layoutY );
			
			//Se adiciona espacio en el eje X.
			layoutX += 116.0;
			
			TextField txtNew = new TextField();
			txtNew.setId( variable );
			txtNew.setLayoutX( layoutX );
			txtNew.setLayoutY( layoutY );
			
			//Se adicionan al panel con scroll.
			ancPane.getChildren().add( lblNew );
			ancPane.getChildren().add( txtNew );
			
			column ++;
			
			//Se adiciona espacio en el eje X y Y.
			layoutX += 178.0;
			
			//Valida la cantidad de columnas creadas para generar la siguiente linea.
			if( column == 2) {
				layoutY += 40.0;
				layoutX = 14.0;
				column = 0;
			}
		}
	}
	
	/**
	 * Retorna las variables asociadas a una plantilla de velocity
	 * @param read
	 * @throws Exception
	 * @author Dv
	 */
	private void readVariablesVelocity( Reader read, List<String> variables )throws Exception{
		
		//Se consultan las variables existentes en la plantilla.
		RuntimeInstance runInstance = new RuntimeInstance();
		Template template = new Template();
		SimpleNode node = runInstance.parse( read, template );
		ParserVisitor visitor = new BaseVisitor() {
			@Override
			public Object visit(final ASTReference node, final Object data) {
			    
				if( ( node != null && node.getFirstToken() != null) && 
						variables.contains( node.getFirstToken().toString() ) == false ) {
					
					System.out.println(node.getFirstToken());
					variables.add( node.getFirstToken().toString() );
				}
				
			    return null;
		    }
		};
		
		visitor.visit(node, null);
	}
	
	/**
	 * Genera el proyecto dependiendo de las 
	 * configuraciones ingresadas.
	 * @author Dv
	 */
	public void actionGenerar() {
		boolean isRequired = true;
		List<String> sourcePathList = null;
		HashMap< String, String > varList = null;
		
		try {
			
			//Valida los datos obligatorios.
			isRequired = validateValuesRequired();
			
			if(!isRequired) {
				mensajes.mostrarMensajeBundleFx("global.label.error.camposRequeridos", mensajes.ALERTA, null);
				return;
			}
			
			//Se ordenan los parametros necesarios para la generacion.
			sourcePathList = getPathsFromConfig();
			varList = getVariablesAndValues();
			
			//Se genera el componente seleccionado.
			proyectoModel.runGenerateComponent( varList, sourcePathList, textCodesJson, inpDestino.getText() );
			
			
			mensajes.mostrarMensajeBundleFx("global.label.done.generacion", mensajes.INFORMACION, null);
			
		}catch(Exception e) {
			System.out.print("ProyectoController.actionGenerar. Causa: "+e.getMessage());
			e.printStackTrace();
			
			try {
				mensajes.mostrarMensajeBundleFx( "global.label.error.generacion" , mensajes.ERROR, e.getMessage());
			}catch( Exception d) {
				
			}
		}
	}
	
	/**
	 * Retorna la variable mostrada dinamicamente
	 * con el valor ingresado por el usuario.
	 * @return
	 * @throws Exception
	 * @author Dv
	 */
	private HashMap<String, String> getVariablesAndValues()throws Exception{
		HashMap<String, String> varList = new HashMap<String, String>();
		
		//Valida los elementos generados dinamicamente.
		for(Node node : ancPane.getChildren()) {
			
			if( node.getClass() == TextField.class ) {
				
				TextField txtField = (TextField) node;
				
				varList.put( txtField.getId() , txtField.getText() );
			}
		}
		
		return varList;
	}
	
	/**
	 * Retorna la lista de direcciones de carpetas
	 * y configuracion de los archivos a generar.
	 * @return
	 * @throws Exception
	 * @author Dv
	 */
	private List<String> getPathsFromConfig() throws Exception{
		List<String> pathList = null;
		String projectPath = null;
		String path = null;
		String sourceVm = null;
		String configFileName = null;
		char signalRoot;
		
		
		//Se carga el archivo de configuracion de la generacion de archivos.
		path = tipoSelectData.get( PARAMETER_TIPO_FIELD_PATH ).getAsString();
		sourceVm = subTipoSelectData.get( PARAMETER_SUBTIPO_FIELD_SRC_VM ).getAsString();
		configFileName = subTipoSelectData.get( PARAMETER_SUBTIPO_FIELD_CONFIG ).getAsString();
		signalRoot = textCodesJson.get( PARAMETER_TEXT_CODES_FIELD_ROOT ).getAsCharacter();
		
		projectPath = System.getProperty("user.dir");
		BufferedReader configFile = new BufferedReader(new FileReader( projectPath + path + sourceVm + configFileName ));
		pathList = new ArrayList<>();
		
		String line = null;
		while( (line = configFile.readLine() ) != null ) {
				
			if( line.charAt( 0 ) == signalRoot ) {
				
				//Adiciona la linea de directorio a la lista.
				pathList.add( line );
				System.out.println( "Source: " + line );
			}
		}
		
		if( configFile !=null ) configFile.close();
		
		return pathList;
	}
	
	/**
	 * Selecciona el destino en el cual se creara el nuevo
	 * proyecto.
	 * @author Dv
	 */
	@FXML
	public void actionSelectDestino(ActionEvent event) {
		DirectoryChooser win = null;
		Node source = (Node)event.getSource();
		Window stage = source.getScene().getWindow();
		File path = null;
		
		try {
			
			win = new DirectoryChooser();
			path = win.showDialog(stage);
			
			if(path!=null) {
				inpDestino.setText(path.getAbsolutePath() + "\\");
			}
		}catch(Exception e) {
			System.out.print("ProyectoController.actionSearchDestino. Causa: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Valida los datos necesarios para el correcto funcionamiento.
	 * @return
	 * @throws Exception
	 * @author Dv
	 */
	private boolean validateValuesRequired()throws Exception{
		boolean isRequired = true;
		
		if( StringUtil.isEmptyOrNull( inpDestino.getText() ) ) {
			isRequired = false;
		}
		else if( StringUtil.isEmptyOrNull( cbxTipo.getSelectionModel().getSelectedItem() ) ||
				cbxTipo.getSelectionModel().getSelectedItem().equals( DEFAULT_VALUE_LIST ) ) {
			isRequired = false;
		}
		
		//Valida los elementos generados dinamicamente.
		for(Node node : ancPane.getChildren()) {
			
			if( node.getClass() == TextField.class ) {
				
				TextField txtField = (TextField) node;
				
				if( StringUtil.isEmptyOrNull( txtField.getText() ) ) {
					isRequired = false;
					break;
				}
			}
		}
		
		return isRequired;
	}
	
	//Acceder al objeto filtro desde el fxml.
	public final ProyectoDTO getProyectoFilter() {
	    return this.proyectoFilter.get();
	}

	public final void setProyectoFilter(ProyectoDTO value) {
	    this.proyectoFilter.set(value);
	}

	public final ObjectProperty<ProyectoDTO> proyectoFilterProperty() {
	    return this.proyectoFilter;
	}
	//Finish.
	
	//Accessors.
	public ProyectoDTO getProyectoSelected() {
		return proyectoSelected;
	}

	public void setProyectoSelected(ProyectoDTO proyectoSelected) {
		this.proyectoSelected = proyectoSelected;
	}

	public IProyectoModel getProyectoModel() {
		return proyectoModel;
	}

	public void setProyectoModel(IProyectoModel proyectoModel) {
		this.proyectoModel = proyectoModel;
	}

	public Button getBtnChooser() {
		return btnChooser;
	}

	public void setBtnChooser(Button btnChooser) {
		this.btnChooser = btnChooser;
	}

	public TextField getInpDestino() {
		return inpDestino;
	}

	public void setInpDestino(TextField inpDestino) {
		this.inpDestino = inpDestino;
	}

	public AnchorPane getAncPane() {
		return ancPane;
	}

	public void setAncPane(AnchorPane ancPane) {
		this.ancPane = ancPane;
	}

	public ComboBox<String> getCbxTipo() {
		return cbxTipo;
	}

	public void setCbxTipo(ComboBox<String> cbxTipo) {
		this.cbxTipo = cbxTipo;
	}

	public ComboBox<String> getCbxSubtipo() {
		return cbxSubtipo;
	}

	public void setCbxSubtipo(ComboBox<String> cbxSubtipo) {
		this.cbxSubtipo = cbxSubtipo;
	}

}
