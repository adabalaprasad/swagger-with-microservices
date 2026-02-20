package com.example.demo.entityclasses;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class StudentAttendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "student_id")
    private Long studentId;
    private LocalDate date;
    private LocalTime loginTime;
    private LocalTime logoutTime;
    private String status;
    
    
	public StudentAttendance() {
		super();
	}


	public StudentAttendance(Long id, Long studentId, LocalDate date, LocalTime loginTime, LocalTime logoutTime,
			String status) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.date = date;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.status = status;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
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
