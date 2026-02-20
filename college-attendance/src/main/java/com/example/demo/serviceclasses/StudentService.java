package com.example.demo.serviceclasses;

import java.awt.print.Pageable;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import com.example.demo.StudentClient;
import com.example.demo.dtoclasses.StudentDTO;
import com.example.demo.dtoclasses.StudentInfoDTO;
import com.example.demo.dtoclasses.StudentInfoResDTO;
import com.example.demo.dtoclasses.StudentLoginDTO;
import com.example.demo.dtoclasses.StudentRegDTO;
import com.example.demo.dtoclasses.StudentReqDTO;
import com.example.demo.dtoclasses.StudentResDTO;
import com.example.demo.dtoclasses.StudentRespDTO;
import com.example.demo.entityclasses.StudentAttendance;
import com.example.demo.entityclasses.StudentAttendanceMongo;
import com.example.demo.entityclasses.StudentRegister;
import com.example.demo.entityclasses.StudentRegisterMongo;
import com.example.demo.repositories.StudentAttendanceMongoRepo;
import com.example.demo.repositories.StudentAttendanceRepo;
import com.example.demo.repositories.StudentMongoRepo;
import com.example.demo.repositories.StudentRegisterRepo;
import com.example.demo.security.JwtUtil;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;

@Service
public class StudentService {

	@Autowired
	StudentAttendanceRepo repo;
	
	
	@Autowired
	StudentRegisterRepo registerRepo;
	

	@Autowired
	RestTemplate template;
	
	@Autowired
	StudentClient studentClient;
	
	@Autowired
	StudentAttendanceMongoRepo mongoRepo;
	
	@Autowired
	StudentMongoRepo mongoRegisterRepo;
	
	
	public StudentRegister register(StudentRegDTO reg)
	{
		 if (registerRepo.findByEmail(reg.getEmail()) != null) 
		 {
	            return null;
	     }

	     if (registerRepo.findByPhoneNumber(reg.getPhoneNumber()) != null) 
	     {
	            return null;
	     }

	        StudentRegister student = new StudentRegister();
	        student.setName(reg.getName());
	        student.setEmail(reg.getEmail());
	        student.setPhoneNumber(reg.getPhoneNumber());
	        student.setPassword(reg.getPassword());
	        student = registerRepo.save(student);
	        
	        StudentRegisterMongo mongo= new StudentRegisterMongo();
	        mongo.setName(reg.getName());
	        mongo.setEmail(reg.getEmail());
	        mongo.setPhoneNumber(reg.getPhoneNumber());
	        mongo.setPassword(reg.getPassword());
	        mongoRegisterRepo.save(mongo);

	        StudentRegister studentReg= new StudentRegister(student.getName(),student.getEmail(),student.getPhoneNumber(),student.getPassword());
	          
	        return studentReg;
	}
	
	
/*	public String login(StudentRegDTO reg) 
	{

	    StudentRegister student = registerRepo.findByEmail(reg.getEmail());

	    if (student == null) 
	    {
	        return null;
	    }

	    if (!student.getPassword().equals(reg.getPassword())) 
	    {
	        return null;
	    }

	    return JwtUtil.generateToken(student.getEmail());
	}
	
	*/
	
	
	public StudentResDTO login(StudentLoginDTO log)
	{
		
		StudentResDTO res=new StudentResDTO();
        StudentRegister student = registerRepo.findByEmail(log.getEmail());

        if (student == null)
        {
            res.setStatus("ERROR");
            res.setMessage("Email not registered");
            return res;
        }

        if (!student.getPassword().equals(log.getPassword())) 
        {
            res.setStatus("ERROR");
            res.setMessage("Invalid password");
            return res;
        }

        res.setStatus("OK");
        res.setMessage("Login successful");
        return res;
	    
	    
	}
	
	
	@CacheEvict(value = {"allAttendancePostCache"},allEntries = true)
	public StudentDTO markAttendance(StudentReqDTO req)
	{
		StudentAttendance student=new StudentAttendance();
		
		student.setStudentId(req.getStudentId());
		student.setDate(LocalDate.now());
		student.setLoginTime(LocalTime.now());
		student.setStatus(req.getStatus());
		student=repo.save(student);
		
		StudentDTO dto=new StudentDTO(student.getStudentId(), student.getDate(), student.getLoginTime(), null, student.getStatus());

		return dto;
	}
	
	public StudentDTO login(Long studentId) {

	    LocalDate today = LocalDate.now();

	    List<StudentAttendance> list = repo.findByStudentIdAndDate(studentId, today);

	    if (!list.isEmpty()) 
	    {
	        return null;
	    }
	    else
	    {
	    StudentAttendance student = new StudentAttendance();
	    student.setStudentId(studentId);
	    student.setDate(today);
	    student.setLoginTime(LocalTime.now());
	    student.setStatus("PRESENT");
	    student = repo.save(student);

	    StudentDTO dto= new StudentDTO(student.getStudentId(),student.getDate(),student.getLoginTime(),null,student.getStatus());
	    
	    return dto;
	    }
	}
	
	@Cacheable(value="allAttendancePostCache")
	public List<StudentDTO> getAllAttendance()
	{
		
		System.out.println("Fetching from Postgresql Data Base");
		
        List<StudentDTO> dtoList = new ArrayList<>();

        List<StudentAttendance> students = repo.findAll();
        for (StudentAttendance student : students) 
        {
            StudentDTO dto = new StudentDTO(student.getStudentId(),student.getDate(),student.getLoginTime(),student.getLogoutTime(),student.getStatus());
            dtoList.add(dto);
        }

        return dtoList;
	}
	
	@Cacheable(value="allAttendanceCache")
	public List<StudentDTO> allAttendance()
	{
		
		System.out.println("Fetching from mongo Data Base");
		
	    List<StudentDTO> dtoList = new ArrayList<>();
		List<StudentAttendanceMongo> mongo=mongoRepo.findAll();
        for (StudentAttendanceMongo student : mongo) 
        {
            StudentDTO dto = new StudentDTO(student.getStudentId(),student.getDate(),student.getLoginTime(),student.getLogoutTime(),student.getStatus());
            dtoList.add(dto);
        }
        
        
        
        return dtoList;
		
	}
	
	
	@Cacheable(value="attendanceCache", key = "#studentId")
	public List<StudentDTO> getAttendance(Long studentId)
	{
		System.out.println("Fetching from DB...");

        List<StudentAttendance> students = repo.findByStudentId(studentId);
        List<StudentDTO> dtoList = new ArrayList<>();

        for (StudentAttendance student : students) 
        {
            StudentDTO dto = new StudentDTO(student.getStudentId(),student.getDate(),student.getLoginTime(),student.getLogoutTime(),student.getStatus());
            dtoList.add(dto);
        }

        return dtoList;
	}
	
	@Transactional
	@CacheEvict(value = "allAttendancePostCache", allEntries=true)
	public StudentDTO deleteAttendance(Long studentId)
	{
		
		List<StudentAttendance> list=repo.findByStudentId(studentId);

	    if (list.isEmpty()) 
	    {
	        return null;
	    }

	    StudentAttendance student = list.get(0);
	    repo.deleteByStudentId(studentId);
	    StudentDTO dto = new StudentDTO(student.getStudentId(),student.getDate(),student.getLoginTime(),student.getLogoutTime(),student.getStatus());

	    return dto;
		
	}
	public String updateNumber(String email,String number) 
	{
		StudentRegister r=registerRepo.findByEmail(email);
		r.setPhoneNumber(number);
		registerRepo.save(r);
		
		return "update number successfully";
	}
	
	/*public List<StudentRegDTO> getAllStudentsFromService() {
	    String url = "http://localhost:2000/all";

	    StudentInfoResDTO response = template.getForObject(URI.create(url), StudentInfoResDTO.class);

	    if(response.getStatus().equalsIgnoreCase("ok")) 
	    {
	        return response.getDto();
	    } 
	    else
	    {
	        return null; 
	    }
	}
	*/
	/*
	public StudentDTO checkAttendance(StudentReqDTO req,Long studentId)
	{
		
		String url="http://localhost:2003/"+studentId;
		
		StudentResDTO res=template.getForObject(URI.create(url), StudentResDTO.class);
		
		if(res != null && "OK".equalsIgnoreCase(res.getStatus()))
		{ 
			
            StudentAttendance attendance = new StudentAttendance();
            attendance.setStudentId(studentId);
            attendance.setDate(LocalDate.now());
            attendance.setLoginTime(LocalTime.now());
            attendance.setStatus(req.getStatus());

            attendance = repo.save(attendance);
            StudentDTO dto = new StudentDTO(attendance.getStudentId(),attendance.getDate(),attendance.getLoginTime(),attendance.getLogoutTime(),attendance.getStatus());

            return dto;
        } 
		else 
		{
            return null; 
        }
	}
	*/
	@CircuitBreaker(name = "scb",fallbackMethod = "fallBack")
	@CacheEvict(value = "allAttendanceCache", key = "#studentId")
	public StudentDTO checkAttendance(StudentReqDTO req,Long studentId)
	{
		
		StudentResDTO res=studentClient.getStudent(studentId);
		
		if(res != null && "OK".equalsIgnoreCase(res.getStatus()))
		{ 
			
            StudentAttendance attendance = new StudentAttendance();
            attendance.setStudentId(studentId);
            attendance.setDate(LocalDate.now());
            attendance.setLoginTime(LocalTime.now());
            attendance.setStatus(req.getStatus());

            attendance = repo.save(attendance);
            
            StudentAttendanceMongo mongo=new StudentAttendanceMongo();
            mongo.setStudentId(studentId);
            mongo.setDate(LocalDate.now());
            mongo.setLoginTime(LocalTime.now());
            mongo.setStatus(req.getStatus());
            mongoRepo.save(mongo);
            
            StudentDTO dto = new StudentDTO(attendance.getStudentId(),attendance.getDate(),attendance.getLoginTime(),attendance.getLogoutTime(),attendance.getStatus());

            return dto;
        } 
		else 
		{
            return null; 
        }
	}
	
	public StudentDTO fallBack(StudentReqDTO req,Long studentId,Exception e)
	{
		StudentDTO dto =new StudentDTO();
		    dto.setStudentId(studentId);
		    dto.setDate(LocalDate.now());
		    dto.setLoginTime(LocalTime.now());
		    dto.setStatus("Student service is down");
		    
		return dto;
	}
	
	   public Long getStudentWithHighestAttendance() {

		    List<Long> result = repo.findStudentWithMaxAttendance();

		    if (result.isEmpty()) {
		        return null;
		    }
		    return result.get(0);
		}
	   
	   public List<StudentInfoDTO> getAllStudentsFromStudentService() 
	   {

		    StudentInfoResDTO res = studentClient.getAllStudents();

		    if (res != null && "OK".equalsIgnoreCase(res.getStatus())) 
		    {
		        return res.getDto();
		    }
		    return null;
		}
	   
	   
}
