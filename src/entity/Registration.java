package entity;

import controller.DBController;
import view.*;

public class Registration {
	private int studentId;
	private String subjectId1;
	private String subjectId2;
	private String subjectId3;
	private String subjectId4;
	private String studentName;
	private int semester;
	private int year;
	DBController db;
	
	public Registration(int studentId, String studentName, int semester, int year, String subjectId1, String subjectId2, String subjectId3, String subjectId4 ){
		this.studentId=studentId;
		this.studentName=studentName;
		this.semester=semester;
		this.year=year;
		this.subjectId1=subjectId1;
		this.subjectId2=subjectId2;
		this.subjectId3=subjectId3;
		this.subjectId4=subjectId4;

	}
	
	public int getSemesterFromDatabase(int studentId, int semester) throws Exception {
		db=new DBController();
		semester=db.getSemesterFromDatabase(studentId);
		return semester;
		
	}
	
	public int getYearFromDatabase(int studentId, int year) throws Exception {
		db=new DBController();
		year=db.getYearFromDatabase(studentId);
		return year;
		
	}
	
	public String getNameFromDatabase(int studentId, String studentName) throws Exception {
		db=new DBController();
		studentName=db.getStudentNameFromDatabase(studentId);
		return studentName;
		
	}
	
	public boolean deleteRecords(int studentId) throws Exception {
		boolean success=false;
		db=new DBController();
		db.removeRecordRegistration(studentId);
		
		return success;
		
	}
	
	public Registration() {
		
	}
	
	public Registration(int studentId) {
		this.studentId=studentId;
	}
	
	public String getName() {
		return studentName;
	}
	public void setName(String studentName) {
		this.studentName = studentName;
	}
	
	public int getStudentId() {
		return studentId;
	}
	public void setId(int studentId) {
		this.studentId=studentId;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year=year;
	}
	
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester=semester;
	}
	
	public String getSubjectId1() {
		return subjectId1;
	}
	public void setSubjectId1(String subjectId1) {
		this.subjectId1=subjectId1;
	}
	
	public String getSubjectId2() {
		return subjectId2;
	}
	public void setSubjectId2(String subjectId2) {
		this.subjectId2=subjectId2;
	}
	
	public String getSubjectId3() {
		return subjectId3;
	}
	public void setSubjectId3(String subjectId3) {
		this.subjectId3=subjectId3;
	}
	
	public String getSubjectId4() {
		return subjectId4;
	}
	public void setSubjectId4(String subjectId4) {
		this.subjectId4=subjectId4;
	}
}
