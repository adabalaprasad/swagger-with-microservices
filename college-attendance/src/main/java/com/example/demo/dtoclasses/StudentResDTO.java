package com.example.demo.dtoclasses;

public class StudentResDTO {

	
	String status;
	String message;
	StudentDTO dto;
	
	public StudentResDTO() {
		super();
	}
	public StudentResDTO(String status, String message, StudentDTO dto) {
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
	public StudentDTO getDto() {
		return dto;
	}
	public void setDto(StudentDTO dto) {
		this.dto = dto;
	}
	
	
}
