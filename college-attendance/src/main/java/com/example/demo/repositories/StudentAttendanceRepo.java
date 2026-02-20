package com.example.demo.repositories;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entityclasses.StudentAttendance;
import com.example.demo.entityclasses.StudentRegister;

public interface StudentAttendanceRepo extends JpaRepository<StudentAttendance, Long> 
{
    
	 List<StudentAttendance> findByStudentId(Long studentId);
	 long deleteByStudentId(Long studentId);
	List<StudentAttendance> findByStudentIdAndDate(Long studentId, LocalDate today);
	
	
	 @Query("""
		        SELECT a.studentId
                FROM StudentAttendance a
                GROUP BY a.studentId
                ORDER BY COUNT(a.id) DESC
		    """)
	 List<Long> findStudentWithMaxAttendance();
}
