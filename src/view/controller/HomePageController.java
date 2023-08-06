package view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import view.LaunchWindow;

public class HomePageController {

    @FXML
    void addNewRecord(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\AddNewStudent.fxml");

    }

    @FXML
    void deleteRecord(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\DeleteRecord.fxml");

    }

    @FXML
    void exit(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();

    }

    @FXML
    void listAllRecords(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\ViewRecords.fxml");

    }

    @FXML
    void modifyRecord(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\ModifyRecord.fxml");
		
    }
    
    @FXML
    void goToMedia(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\Media.fxml");
    }

}