package view.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.DBController;
import entity.Registration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import view.LaunchWindow;

public class ListAllRecords{

    @FXML
    private TableColumn<Registration, Integer> tcId;

    @FXML
    private TableColumn<Registration, String> tcName;

    @FXML
    private TableColumn<Registration, Integer> tcSemester;

    @FXML
    private TableColumn<Registration, String> tcSubjects1;
    
    @FXML
    private TableColumn<Registration, String> tcSubjects2;
    
    @FXML
    private TableColumn<Registration, String> tcSubjects3;
    
    @FXML
    private TableColumn<Registration, String> tcSubjects4;

    @FXML
    private TableColumn<Registration, Integer> tcYear;

    @FXML
    private TableView<Registration> tv;
    
    @FXML
    void backToHomepage(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\HomePage.fxml");
    }

    public void initialize() {
		ArrayList <Registration>list = null;
    	
    	tcId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
    	tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	tcSemester.setCellValueFactory(new PropertyValueFactory<>("semester"));
    	tcYear.setCellValueFactory(new PropertyValueFactory<>("year"));
    	tcSubjects1.setCellValueFactory(new PropertyValueFactory<>("subjectId1"));
    	tcSubjects2.setCellValueFactory(new PropertyValueFactory<>("subjectId2"));
    	tcSubjects3.setCellValueFactory(new PropertyValueFactory<>("subjectId3"));
    	tcSubjects4.setCellValueFactory(new PropertyValueFactory<>("subjectId4"));

    	try {
    		list = new DBController().getAllStudents();
    		for(Registration reg : list)
    			tv.getItems().add(reg);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    		LaunchWindow.showDialog(null,"error", "error connecting to the database");
    	}		
	}





}
