package controller;
import java.sql.*;
import java.util.ArrayList;

import entity.Registration;
import entity.Student;
import model.*;
import view.*;


public class DBController {
	Statement stmt; 
	Validation v;
	Student stud;
	Registration rg;
	
	public DBController() throws Exception {
		String url = "jdbc:mysql://localhost:3306/assignment2";
		String login = "root";
		String pword = "";
		Connection connect = DriverManager.getConnection(url, login,pword);
		 stmt  = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

	}
	
	public boolean checkId(int id)throws Exception{
		   boolean success=false;
		   
		   String sql=String.format("Select * FROM registration WHERE studentid=('%d')", id);
		   ResultSet result=stmt.executeQuery(sql);
		   
	        if(result.next()) {
	        	success=true;
	        	
	        }
	        else {
	        	success=false;
	        }
			return success;

	   }
	   
	   public int insertRecordRegisteration (Registration reg) throws Exception {
	   int row=0;	
		   
		String sql = String.format("insert into registration(StudentId,studentName,semester,year,SubjectId1,SubjectId2,SubjectId3,SubjectId4) "
				 + "values(%d, '%s', %d , %d, '%s', '%s', '%s', '%s')", reg.getStudentId(), reg.getName(), reg.getSemester(),reg.getYear(), reg.getSubjectId1(), reg.getSubjectId2(), reg.getSubjectId3(), reg.getSubjectId4());
		try {
			row = stmt.executeUpdate(sql);
			return row;
		} catch (SQLException ex) {
				throw new Exception("Error adding record into the database");
		}	
	}
	
	public int insertRecordStudent(Student stud)throws Exception{
		int row=0;
		
		String sql = String.format("insert into student(StudentId,StudentName) values(%d, '%s')", stud.getId(), stud.getName());
		try {
			row = stmt.executeUpdate(sql);
			return row;
		} catch (SQLException ex) {
				throw new Exception("Error adding record into the database");
		}		
	}
		
	public int removeRecordRegistration (Registration reg) throws Exception {
		int row=0;
		
		String sql = String.format("delete from registration where StudentId=('%d')", reg.getStudentId());
		try {
			row = stmt.executeUpdate(sql);
			return row;
		} catch (SQLException ex) {
			throw new Exception("Error adding record into the database");
		}

	}
	
	public boolean removeRecordRegistration (int id) throws Exception {
		boolean success = false;
		
		String sql = String.format("delete from registration where StudentId=('%d')", id);
		int num = stmt.executeUpdate(sql);
		return num==1;
	}
	
	public ArrayList <Registration> getAllStudents () throws Exception  {
		ArrayList <Registration> list = null;
		ResultSet result = null;
		int count=0;
		String sql = "select * from registration order by StudentId";
		try {			
			result = stmt.executeQuery(sql);
			list = new ArrayList <Registration> ();
			while (result.next()) {
				list.add(new Registration(result.getInt("studentId"), result.getString("StudentName"), result.getInt("semester"), result.getInt("Year"), result.getString("subjectId1"), result.getString("subjectId2"), result.getString("subjectId3"), result.getString("subjectId4")));
			}
			return list;
		} catch (SQLException ex) {
			throw new Exception("Error retrieving student records from database");
		}
	}
	
	
		public ArrayList <Registration> getOneStudent (int id) throws Exception  {
			ArrayList <Registration> list = null;
			ResultSet result = null;
			int count=0;
	        String sql=String.format("select * from registration WHERE studentid=('%d')",id);
			try {			
				result = stmt.executeQuery(sql);
				list = new ArrayList <Registration> ();
				while (result.next()) {
					list.add(new Registration(result.getInt("StudentId"), result.getString("StudentName"), result.getInt("Semester"), result.getInt("Year"), result.getString("subjectId1"), result.getString("subjectId2"), result.getString("subjectId3"), result.getString("subjectId4")));
				}
				return list;
			} catch (SQLException ex) {
				throw new Exception("Error retrieving student records from database");
			}
		}

	   
	   public int getSemesterFromDatabase(int id) throws Exception {
		   boolean success=false;
		   int semester=0;
		   
		   String sql=String.format("Select * FROM registration WHERE studentId=('%d')", id);
		   ResultSet result=stmt.executeQuery(sql);
		   
		   if(result.next()) {
		   semester=result.getInt("Semester");
		   
		   }
		   
		   return semester;
	   }
	   
	   public int getYearFromDatabase(int id) throws Exception {
		   boolean success=false;
		   int year=0;
		   
		   String sql=String.format("Select * FROM registration WHERE studentId=('%d')", id);
		   ResultSet result=stmt.executeQuery(sql);
		   
		   if(result.next()) {
		   year=result.getInt("Year");
		   
		   }
		   
		   return year;
	   }
	   

	   
	   public String getStudentNameFromDatabase(int studentId)throws Exception{
		   String studentName=" ";
		   String sql=String.format("select * from registration where studentId=('%d')",studentId);
		   ResultSet result=stmt.executeQuery(sql);
		   
		   if(result.next()) {
			   studentName=result.getString("studentName");
			   }
			   
			   return studentName;
	   }
			
}