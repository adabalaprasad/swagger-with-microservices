package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dtoclasses.StudentInfoResDTO;
import com.example.demo.dtoclasses.StudentResDTO;

@FeignClient(name = "STUDENTSERV")
public interface StudentClient 
{

	 @GetMapping("/{studentId}")
	 StudentResDTO getStudent(@PathVariable Long studentId);
	 
	 @GetMapping("/all")
	 StudentInfoResDTO getAllStudents();
	
}
