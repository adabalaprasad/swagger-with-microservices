package com.example.demo.dtoclasses;

import java.util.List;

import com.example.demo.entityclasses.Student;

public class StudentRespDTO 
{

	String status;
	String message;
	List<Student> dto;
	public StudentRespDTO() {
		super();
	}
	public StudentRespDTO(String status, String message, List<Student> dto) {
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
	public List<Student> getDto() {
		return dto;
	}
	public void setDto(List<Student> dto) {
		this.dto = dto;
	}
	
	
}
