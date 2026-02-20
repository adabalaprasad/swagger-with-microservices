package com.example.demo.dtoclasses;
import java.util.*;

public class StudentInfoResDTO 
{

	private String status;
    private String message;
    List<StudentInfoDTO> dto;
    
	public StudentInfoResDTO() {
		super();
	}
	public StudentInfoResDTO(String status, String message, List<StudentInfoDTO> dto) {
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
	public List<StudentInfoDTO> getDto() {
		return dto;
	}
	public void setDto(List<StudentInfoDTO> dto) {
		this.dto = dto;
	}
    
    
}
