package com.example.demo.serviceclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entityclasses.Student;
import com.example.demo.entityclasses.StudentMongo;
import com.example.demo.repositories.StudentMongoRepo;
import com.example.demo.repositories.StudentRepo;

@Service
public class StudentService 
{

	  @Autowired
	  private StudentRepo repo;
	  
	  @Autowired
	  StudentMongoRepo mongoRepo;

	  public Student addStudent(Student student) 
	  {
	      Student saveStudent=repo.save(student);
	      
	      // Convert to Mongo Document
	      StudentMongo mongoStudent = new StudentMongo();
	      mongoStudent.setStudentId(student.getStudentId());
	      mongoStudent.setStudentName(student.getStudentName());
	      mongoStudent.setStudentEmail(student.getStudentEmail());

	      // Save in MongoDB
	      mongoRepo.save(mongoStudent);
	      
	      return saveStudent;
	  }
	  
	  public List<Student> getAllStudents() 
	  {
	      List<Student> list=repo.findAll();
	      List<Student> students=new ArrayList<>(list);
	      
	      return students;
		 
	  }

	  public Optional<Student> getStudentById(Long studentId) 
	  {
		  Optional<Student> student = repo.findByStudentId(studentId);
		  
		  return student;
	  }


	  public Student updateStudent(Long studentId, Student student) 
	  {
		  Optional<Student> s = repo.findByStudentId(studentId);

		    if (s.isPresent()) 
		    {

		        Student stu = s.get();

		        stu.setStudentName(student.getStudentName());
		        stu.setStudentEmail(student.getStudentEmail());
		        

		        return repo.save(stu);
		    }

		    return null;
	  }

	 public boolean deleteStudent(Long studentId)
	 {
	      Optional<Student> student = repo.findByStudentId(studentId);

		    if (student.isPresent()) {
		        repo.deleteByStudentId(studentId);
		        return true;
		    }
		    return false;
	 }
}
