package com.example.demo.dtoclasses;

import com.example.demo.entityclasses.Student;

public class StudentResDTO 
{
	
	String status;
	String message;
	Student dto;
	public StudentResDTO() {
		super();
	}
	public StudentResDTO(String status, String message, Student dto) {
		super();
		this.status = status;
		this.message = message;
		this.dto = dto;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Student getDto() {
		return dto;
	}
	public void setDto(Student dto) {
		this.dto = dto;
	}
	
	

}
