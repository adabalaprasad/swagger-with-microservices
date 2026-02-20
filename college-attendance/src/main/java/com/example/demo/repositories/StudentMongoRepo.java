package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entityclasses.StudentRegisterMongo;

public interface StudentMongoRepo extends MongoRepository<StudentRegisterMongo, String> 
{
	
	

}
