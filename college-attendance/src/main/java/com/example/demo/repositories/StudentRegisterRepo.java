package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entityclasses.StudentRegister;


public interface StudentRegisterRepo extends JpaRepository<StudentRegister, Integer> 
{

	StudentRegister findByEmail(String email);
    StudentRegister findByPhoneNumber(String phoneNumber);
	StudentRegister save(StudentRegister student);
	StudentRegister findByPassword(String password);
}
