package com.example.demo.entityclasses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Student 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long studentId;
	@NotBlank(message = "Student name is mandatory")
	String studentName;
	
	@NotBlank(message = "Student email is mandatory")
    @Email(message = "Invalid email format")
	String studentEmail;
	public Student() {
		super();
	}
	public Student( Long studentId, String studentName, String studentEmail) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
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
