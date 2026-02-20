package com.example.demo.dtoclasses;

import java.time.LocalDate;
import java.time.LocalTime;

public class StudentReqDTO {

	    private Long studentId;
	    private LocalDate date;
	    private LocalTime loginTime;
	    private LocalTime logoutTime;
	    private String status;
	    
		public StudentReqDTO() {
			super();
		}

		public StudentReqDTO(Long studentId, LocalDate date, LocalTime loginTime, LocalTime logoutTime, String status) {
			super();
			this.studentId = studentId;
			this.date = date;
			this.loginTime = loginTime;
			this.logoutTime = logoutTime;
			this.status = status;
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
