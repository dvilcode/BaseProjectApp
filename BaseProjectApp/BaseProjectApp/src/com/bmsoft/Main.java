package com.bmsoft;
	
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import com.bmsoft.controllers.ProyectoController;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			ResourceBundle bundle = ResourceBundle.getBundle("com.bmsoft.views.i18n.labels");
			
			FXMLLoader fx = new FXMLLoader(getClass().getResource("views/proyectoCreate.fxml"), bundle);
//			ProyectoController controller = fx.<ProyectoController>getController();
//			fx.setController(controller);
			
			Parent root = fx.load();

			Scene scene = new Scene(root,900,490);
			scene.getStylesheets().add(getClass().getResource("views/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("BPA (Base Project App)");
			primaryStage.show();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
