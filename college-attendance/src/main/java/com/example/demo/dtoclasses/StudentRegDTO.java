package com.example.demo.dtoclasses;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Schema(description = "Student Registration Request")
public class StudentRegDTO {

    @NotBlank(message = "Name is mandatory")
    @Schema(description = "Student Name", required = true)
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    @Schema(description = "Student Email", required = true)
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @Schema(description = "10 Digit Phone Number", required = true)
    private String phoneNumber;

    @NotBlank(message = "Password is mandatory")
    @Schema(description = "Student Password", required = true)
    private String password;

	
	public StudentRegDTO() {
		super();
	}
	public StudentRegDTO(String name, String email, String phoneNumber, String password) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
