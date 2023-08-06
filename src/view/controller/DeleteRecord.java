package view.controller;

import java.util.ArrayList;

import controller.DBController;
import entity.Registration;
import entity.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import view.LaunchWindow;

public class DeleteRecord {
	DBController db;

	public DeleteRecord() throws Exception{
		db=new DBController();

	}
	
	 @FXML
	 private TableColumn<Registration, Integer> tcId;

	  @FXML
	  private TableColumn<Registration, String> tcName;

	    @FXML
	    private TableView<Registration> tvRecord;

	    @FXML
	    private TableColumn<Registration, Integer> tcSemester;

	    @FXML
	    private TableColumn<Registration, String> tcSubjectId1;

	    @FXML
	    private TableColumn<Registration, String> tcSubjectId2;

	    @FXML
	    private TableColumn<Registration, String> tcSubjectId3;

	    @FXML
	    private TableColumn<Registration, String> tcSubjectId4;

	    @FXML
	    private TableColumn<Registration, Integer> tcYear;
	
	@FXML
	private TextField tfId;
	

	   @FXML
	    void checkId(ActionEvent event) throws Exception {
	    	ArrayList <Registration>list = null;
	    	boolean validation;
	    	int id=0;
	    	
			try {
				id=Integer.parseInt(tfId.getText());			
			}catch(Exception ex) {
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				LaunchWindow.showInvalid(stage, "ERROR", "Please use a 6 digit number for the student id");
			}
			
			if(id<100000 || id>999999) {
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				LaunchWindow.showInvalid(stage, "ERROR", "ID must be a 6 digit number");

			}
			
			
			else {
		     validation=db.checkId(id);
		        if(validation==true) {


			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			LaunchWindow.showDialog(stage,"CONTINUE","Student ID is already in database");
				
	    	tcId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
	    	tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
	    	tcSemester.setCellValueFactory(new PropertyValueFactory<>("semester"));
	    	tcYear.setCellValueFactory(new PropertyValueFactory<>("year"));
	    	tcSubjectId1.setCellValueFactory(new PropertyValueFactory<>("subjectId1"));
	    	tcSubjectId2.setCellValueFactory(new PropertyValueFactory<>("subjectId2"));
	    	tcSubjectId3.setCellValueFactory(new PropertyValueFactory<>("subjectId3"));
	    	tcSubjectId4.setCellValueFactory(new PropertyValueFactory<>("subjectId4"));

	    	try {
	    		list = new DBController().getOneStudent(id);
	    		for(Registration reg : list)
	    			tvRecord.getItems().add(reg);
	    	}catch(Exception ex) {
	    		ex.printStackTrace();
	    		LaunchWindow.showDialog(null,"error", "error connecting to the database");
	    	}		
			}
		        else {
		    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		    		LaunchWindow.showInvalid(stage,"ERROR","Student ID does not exist in database");

		        }
			}
			

	    }

	@FXML
	void deleteRecord(ActionEvent event) throws Exception {
		boolean validation;
		int id=0;
		int row;

		try {
			id=Integer.parseInt(tfId.getText());
		}catch(Exception ex) {
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			LaunchWindow.showInvalid(stage, "ERROR", "Please use a 6 digit number for the student id");
			LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\DeleteRecord.fxml");
		}

		validation=db.checkId(id);
		
		if(id<100000 || id>999999) {
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			LaunchWindow.showInvalid(stage, "ERROR", "ID must be a 6 digit number");
			LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\DeleteRecord.fxml");

		}


		else {

			if(validation!=true) {
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				LaunchWindow.showInvalid(stage, "ERROR", "Student ID does not exist");
				LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\DeleteRecord.fxml");

			}

			else {

				Registration reg = new Registration(id);
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

				try {
					row= new DBController().removeRecordRegistration(reg);
					if(row==1) {
						LaunchWindow.showDialog(stage, "Success", "Record deleted succesfully");
					}
					else {
						LaunchWindow.showDialog(stage, "Error", "Error deleting record from database");
						reset(event);
					}
				}catch(Exception ex) {
					ex.printStackTrace();
					LaunchWindow.showDialog(stage, "Error", "Error connecting to database");
				}
			}
		}
	}

	@FXML
	void reset(ActionEvent event) {
		tfId.setText("");

	}

	@FXML
	void returnToHomepage(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\HomePage.fxml");
	}

}