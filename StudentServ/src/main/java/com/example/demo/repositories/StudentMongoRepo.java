package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entityclasses.Student;
import com.example.demo.entityclasses.StudentMongo;

public interface StudentMongoRepo extends MongoRepository<StudentMongo, String> 
{
	

}
