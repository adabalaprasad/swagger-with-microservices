package com.example.demo.dtoclasses;

import java.util.List;

public class StudentRespDTO {

	String status;
	String message;
	List<StudentDTO> dto;
	
	
	public StudentRespDTO() {
		super();
	}
	
	public StudentRespDTO(String status, String message, List<StudentDTO> dto) {
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
	public List<StudentDTO> getDto() {
		return dto;
	}
	public void setDto(List<StudentDTO> dto) {
		this.dto = dto;
	}
	
	
}
