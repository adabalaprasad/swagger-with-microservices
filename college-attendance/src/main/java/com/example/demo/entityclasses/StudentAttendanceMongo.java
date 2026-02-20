package com.example.demo.entityclasses;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="studentattendance")
public class StudentAttendanceMongo 
{

	@Id
	private String id;
	
	private Long studentId;
    private LocalDate date;
    private LocalTime loginTime;
    private LocalTime logoutTime;
    private String status;
	public StudentAttendanceMongo() {
		super();
	}
	public StudentAttendanceMongo(String id, Long studentId, LocalDate date, LocalTime loginTime, LocalTime logoutTime,
			String status) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.date = date;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.status = status;
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(LocalTime loginTime) {
		this.loginTime = loginTime;
	}
	public LocalTime getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(LocalTime logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
