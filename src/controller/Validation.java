package controller;

import model.*;
import view.*;
import java.sql.*;


public class Validation {
	Statement stmt;
	String errorMessage="invalid value";

	public Validation() throws Exception {
		
	}
	
	public static String getSubjectId() {
		
		for (int cnt=0; cnt<Constants.SUBJECT_ARR.length; cnt++) {
			System.out.printf("%d--%s\n",cnt,Constants.SUBJECT_ARR[cnt]);
		}

		
		return null;
	}
	
	
	public static String getSubjectName (String subId) {
		for (int cnt=0; cnt<Constants.SUBJECT_ARR_SHOWALLREGISTRATION.length; cnt++) {
			if (subId.equals(Constants.SUBJECT_ARR_SHOWALLREGISTRATION[cnt]))
				return Constants.SUBJECT_NAME_ARR[cnt];
		}
		return null;
	}
	
	public static boolean semesterValidation(int semester) {
		boolean valid=false;
		if(semester==1 || semester== 5 || semester==8) {
			valid=true;
		}
		else {
			valid=false;
		}
		return valid;
	}
	
	
	public static boolean idValidation(int id) {
		boolean valid=false;
		if(id>=100000 && id<=999999) {
			valid=true;
		}
		else {
			valid=false;
		}
		
		return valid;
	}
	

	
	
	public static boolean yearValidation(int year) {
		boolean valid=false;
		if(year>=00 && year<=99) {
			valid=true;
		}
		else if(year<00 || year>99) {
			valid=false;
		}
		return valid;
	}
	
	public static boolean subjectIdValidation(String subjectId) {
		boolean valid=false;
		
		for(int cnt=0; cnt<Constants.SUBJECT_ARR.length; cnt++) {
			if(subjectId==Constants.SUBJECT_ARR[cnt]) {
				valid=true;
			}
			else {
				valid=false;
			}
		}
		return valid;
	}
	
	public static boolean subjectCountValidation(int subjects) {
		boolean valid=false;
		
		if(subjects>=2 && subjects<=4) {
			valid=true;
		}
		else if(subjects<2 || subjects>4) {
			valid=false;
		}
		return valid;
		
	}
	
	public static boolean subjectSelectValidation(int subjects) {
		boolean valid=false;
		
		if(subjects>=0 && subjects<=9) {
			valid=true;
		}
		else if(subjects<0 || subjects>9) {
			valid=false;
		}
		return valid;
		
	}

}
