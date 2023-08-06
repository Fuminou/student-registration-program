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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Constants;
import view.LaunchWindow;

public class ModifyRecordController implements Initializable{
	DBController db;
	Registration r;

	public ModifyRecordController() throws Exception{
		db = new DBController();
		r= new Registration();
	}

	@FXML
	private ComboBox<Integer> cbSemester;

	@FXML
	private ComboBox<String> cbSubjects1;

	@FXML
	private ComboBox<String> cbSubjects2;

	@FXML
	private ComboBox<String> cbSubjects3;

	@FXML
	private ComboBox<String> cbSubjects4;

	@FXML
	private ComboBox<Integer> cbYear;

	@FXML
	private TextField tfId;

	@FXML
	private TableColumn<Registration, Integer> tcId;

	@FXML
	private TableColumn<Registration, String> tcName;

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
	private TableView<Registration> tvRecord;

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
			LaunchWindow.showDialog(stage,"Student Id detected","Student ID found in database, please proceed");
				
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
		    		LaunchWindow.showDialog(stage,"ERROR","Student Id does not exist in database");

		        }
			}
			

	    }

	@FXML
	void reset(ActionEvent event) {
		tfId.setText("");
		cbYear.getSelectionModel().clearSelection();
		cbSubjects1.getSelectionModel().clearSelection();
		cbSubjects2.getSelectionModel().clearSelection();
		cbSubjects3.getSelectionModel().clearSelection();
		cbSubjects4.getSelectionModel().clearSelection();
		cbSemester.getSelectionModel().clearSelection();

	}

	@FXML
	void backToHomepage(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\HomePage.fxml");
	}

	@FXML
	void submitRecord(ActionEvent event) throws Exception {
		int row;
		int row2;
		int year=0;
		int yearCheck=0;
		int semester=0;
		int semesterCheck=0;
		int id = 0;
		boolean validation;
		String name=null;
		String subject1;
		String subject2;
		String subject3;
		String subject4;

		try {
			id=Integer.parseInt(tfId.getText());
		}catch(Exception ex) {
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			LaunchWindow.showInvalid(stage, "ERROR", "Please use a 6 digit number for the student id");
			LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\ModifyRecord.fxml");
		}    	

		validation=db.checkId(id);
		if(validation!=true) {
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			LaunchWindow.showInvalid(stage, "ERROR", "Student ID does not exist");
			LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\ModifyRecord.fxml");

		}

		else {


			semesterCheck=r.getSemesterFromDatabase(id, semester);
			yearCheck=r.getYearFromDatabase(id, year);
			name=r.getNameFromDatabase(id,name);
			semester=cbSemester.getValue();
			year=cbYear.getValue();
			subject1=cbSubjects1.getValue();
			subject2=cbSubjects2.getValue();
			subject3=cbSubjects3.getValue();
			subject4=cbSubjects4.getValue();

			if(id<100000 || id>999999) {
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				LaunchWindow.showInvalid(stage, "ERROR", "ID must be a 6 digit number");
			}


			else {

				if(semester!=semesterCheck || year!=yearCheck) {
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					LaunchWindow.showInvalid(stage, "ERROR", "Please check the year and semester");
				}

				else {

					Registration reg = new Registration(id,name,semester,year,subject1,subject2,subject3,subject4);
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					try {
						row2=new DBController().removeRecordRegistration(reg);
						row= new DBController().insertRecordRegisteration(reg);
						if(row==1) {
							LaunchWindow.showDialog(stage, "Success", "Record inserted succesfully");
						}
						else {
							LaunchWindow.showDialog(stage, "Error", "Error inserting record to database");
							reset(event);
						}
					}catch(Exception ex) {
						ex.printStackTrace();
						LaunchWindow.showDialog(stage, "Error", "Error connecting to database");
					}
				}
			}
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbSemester.getItems().add(1);
		cbSemester.getItems().add(5);
		cbSemester.getItems().add(8);

		for(int i=2000;i<=2021;i++)
			cbYear.getItems().add(i);

		for (int cnt=0; cnt<Constants.SUBJECT_ARR.length; cnt++) {
			cbSubjects1.getItems().add(Constants.SUBJECT_ARR[cnt]+Constants.SUBJECT_NAME_ARR[cnt]);
			cbSubjects2.getItems().add(Constants.SUBJECT_ARR[cnt]+Constants.SUBJECT_NAME_ARR[cnt]);
			cbSubjects3.getItems().add(Constants.SUBJECT_ARR[cnt]+Constants.SUBJECT_NAME_ARR[cnt]);
			cbSubjects4.getItems().add(Constants.SUBJECT_ARR[cnt]+Constants.SUBJECT_NAME_ARR[cnt]);    	}
	}

}
