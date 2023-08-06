package view.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.DBController;
import entity.Registration;
import entity.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Constants;
import view.LaunchWindow;

public class AddNewStudentController implements Initializable{
	DBController db;
	
	public AddNewStudentController() throws Exception{
		db=new DBController();

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
    private TextField tfId;

    @FXML
    private TextField tfName;

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
			//LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\AddNewStudent.fxml");
		}
		
		if(id<100000 || id>999999) {
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			LaunchWindow.showInvalid(stage, "ERROR", "ID must be a 6 digit number");
			//LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\AddNewStudent.fxml");

		}
		
		
		else {
	     validation=db.checkId(id);
	        if(validation==true) {


		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		LaunchWindow.showDialog(stage,"ERROR","Student ID is already in database");
			
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
	    		LaunchWindow.showDialog(stage,"New Student ID detected","Student ID does not exist in database, please proceed");

	        }
		}
		

    }

	@FXML
	void add(ActionEvent event) throws Exception {
		int row = 1;
		int row2;
		int year;
		int semester;
		int id = 0;
        boolean validation=false;
		String name;
		String subject1;
		String subject2;
		String subject3;
		String subject4;

		try {
			id=Integer.parseInt(tfId.getText());			
			
		}catch(Exception ex) {
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			LaunchWindow.showInvalid(stage, "ERROR", "Please use a 6 digit number for the student id");
			//LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\AddNewStudent.fxml");
		}
		
        validation=db.checkId(id);
        if(validation==true) {
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

			LaunchWindow.showInvalid(stage, "ERROR", "Student ID already exists");
			//LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\AddNewStudent.fxml");
			
        }
        else {

		if(id<100000 || id>999999) {
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			LaunchWindow.showInvalid(stage, "ERROR", "ID must be a 6 digit number");
			//LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\AddNewStudent.fxml");

		}
		
		
		else {

			name = tfName.getText();

			semester=cbSemester.getValue();
			year=cbYear.getValue();
			subject1=cbSubjects1.getValue();
			subject2=cbSubjects2.getValue();
			subject3=cbSubjects3.getValue();
			subject4=cbSubjects4.getValue();


			Student stud = new Student(id,name);
			Registration reg = new Registration(id,name,semester,year,subject1,subject2,subject3,subject4);
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			try {
				row= new DBController().insertRecordStudent(stud);
				row2= new DBController().insertRecordRegisteration(reg);
				if(row==1 &&row2==1) {
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

    @FXML
	void back(ActionEvent event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		LaunchWindow.showWindow(stage, "D:\\programming eclipse 2\\workspace\\Assignment 2\\src\\view\\HomePage.fxml");

	}

    @FXML
	void reset(ActionEvent event) {
		tfName.setText("");
		tfId.setText("");
		cbSemester.getSelectionModel().clearSelection();
		cbSubjects1.getSelectionModel().clearSelection();
		cbSubjects2.getSelectionModel().clearSelection();
		cbSubjects3.getSelectionModel().clearSelection();
		cbSubjects4.getSelectionModel().clearSelection();
		cbYear.getSelectionModel().clearSelection();
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
			cbSubjects4.getItems().add(Constants.SUBJECT_ARR[cnt]+Constants.SUBJECT_NAME_ARR[cnt]);

		}

	}
	
	public void showDialogError(Window window, String title, String msg) {
		Alert alert = new Alert(AlertType.ERROR, msg, ButtonType.CLOSE);
		alert.setTitle(title);
		alert.initOwner(window);
		alert.show();
	}
    
}