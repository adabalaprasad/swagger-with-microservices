package com.example.demo;

public class LoginResDTO 
{

	 private String status;
	 private String token;
	 private String message;
	 
	 
	public LoginResDTO() {
		super();
	}
	public LoginResDTO(String status, String token, String message) {
		super();
		this.status = status;
		this.token = token;
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	 
	 
}
