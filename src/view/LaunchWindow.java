package view;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LaunchWindow extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		showWindow(stage,"D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\HomePage.fxml");
	}
	
	public static void showWindow(Stage stage, String filename) {
		FXMLLoader loader;
		Scene scene;
		
		try {
			loader = new FXMLLoader(new File(filename).toURI().toURL());
			scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.show();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	/*
	public static void showDialog(Window window, String title, String msg) {
		Alert alert = new Alert(AlertType.NONE, msg, ButtonType.OK);
		alert.setTitle(title);
		alert.initOwner(window);
		alert.show();
	}
	*/

	
	public static void showDialog(Stage stage, String title, String msg) {
		Alert alert = new Alert(AlertType.NONE, msg, ButtonType.OK);
		alert.setTitle(title);
		alert.initOwner(stage);
		alert.show();		
	}
	
	public static void showInvalid(Stage stage, String title, String msg) {
		Alert alert = new Alert(AlertType.WARNING, msg, ButtonType.OK);
		alert.setTitle(title);
		alert.initOwner(stage);
		alert.show();		
	}
	


		
}
