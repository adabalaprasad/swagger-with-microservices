package com.example.demo.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entityclasses.Student;

public interface StudentRepo extends JpaRepository<Student, Long>
{

	Optional<Student> findByStudentId(Long studentId);

	  @Transactional
	  @Modifying
	void deleteByStudentId(Long studentId);
}
