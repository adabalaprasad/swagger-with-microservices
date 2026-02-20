package com.example.demo.entityclasses;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "studentdetails")
public class StudentMongo {

	@Id
	String id;
	
	Long studentId;
	String studentName;
	String studentEmail;
	public StudentMongo() {
		super();
	}
	public StudentMongo(String id, Long studentId, String studentName, String studentEmail) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	
	
}
