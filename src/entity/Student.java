package entity;

public class Student {
	private String name;
	private int studentId;

	public Student(int studentId, String name) {
		this.name = name;
		this.studentId = studentId;
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return studentId;
	}
	public void setId(int studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", studentId=" + studentId + "]";
	}


}