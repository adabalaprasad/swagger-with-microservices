package com.example.demo.dtoclasses;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class StudentLoginDTO 
{

	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Enter valid Email")
	private String email;

	@Schema(requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "Password is mandatory")
	private String password;
	
	
	public StudentLoginDTO() {
		super();
	}

	public StudentLoginDTO(String email,String password)
	{
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
