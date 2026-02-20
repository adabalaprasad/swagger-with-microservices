package com.example.demo.controllerclasses;

import java.util.List;
import java.io.ByteArrayOutputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtoclasses.LoginResDTO;
import com.example.demo.dtoclasses.StudentDTO;
import com.example.demo.dtoclasses.StudentInfoDTO;
import com.example.demo.dtoclasses.StudentInfoResDTO;
import com.example.demo.dtoclasses.StudentLoginDTO;
import com.example.demo.dtoclasses.StudentRegDTO;
import com.example.demo.dtoclasses.StudentReqDTO;
import com.example.demo.dtoclasses.StudentResDTO;
import com.example.demo.dtoclasses.StudentRespDTO;
import com.example.demo.entityclasses.StudentRegister;
import com.example.demo.repositories.StudentRegisterRepo;
import com.example.demo.security.JwtUtil;
import com.example.demo.serviceclasses.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:2003")
@RestController
@RequestMapping("")
@Tag(name = "College Attendance APIs", description = "Student Registration, Login and Attendance Management APIs")
public class StudentController {

	@Autowired
	StudentService service;
	
	@Autowired
	StudentRegisterRepo registerRepo;
	
	@PostMapping("/register")
	@Operation(summary = "Student Registration", description = "Register a new student")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Student registered successfully"),
        @ApiResponse(responseCode = "400", description = "Email or phone already exists")
    })
	public StudentResDTO register(@Valid @RequestBody StudentRegDTO reg)
	{
	     StudentRegister register=service.register(reg);
	     StudentResDTO res=new StudentResDTO();
	     if(register!=null)
	     {
	    	 res.setStatus("ok");
	    	 res.setMessage("Register Successfully");
	     }
	     else
	     {
	    	 res.setStatus("ERROR");
	    	 res.setMessage("Email or Phone Number Already Exists");
	     }
	     return res;
	}
	
	@GetMapping("/by-email/{email}")
	@SecurityRequirement(name = "bearer-key") 
	public StudentRegister getByEmail(@PathVariable String email) 
	{
	    return registerRepo.findByEmail(email);
	}

	
	
/*	@PostMapping("/login")
	@Operation(summary = "Student Login", description = "Login using email and password, returns JWT token")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Login successful"),
        @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
	public LoginResDTO login(@RequestBody StudentRegDTO reg) 
	{

	    String token = service.login(reg);

	    LoginResDTO res = new LoginResDTO();

	    if (token == null) 
	    {
	        res.setStatus("ERROR");
	        res.setMessage("Invalid email or password");
	       
	    }
	    else
	    {
	    res.setStatus("OK");
	    res.setMessage("Login successful");
	    res.setToken(token);
	    }
	    return res;
	}
	*/
	
	
	@PostMapping("/login")
	@Operation(summary = "Student Login", description = "Login using email and password, returns JWT token")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Login successful"),@ApiResponse(responseCode = "401", description = "Invalid credentials")})
	public StudentResDTO login(@Valid @RequestBody StudentLoginDTO log)
	{
		
		 return service.login(log);
		
	}
	
	
	
	@Operation(summary = "Student Daily Login", description = "Login student by ID for attendance day")
	@PostMapping("/login/{studentId}")
	public StudentResDTO login(@PathVariable Long studentId) {

	    StudentDTO dto = service.login(studentId);
	    StudentResDTO res = new StudentResDTO();

	    if (dto != null) 
	    {
	        res.setStatus("OK");
	        res.setMessage("Login successful");
	        res.setDto(dto);
	    } 
	    else 
	    {
	        res.setStatus("ERROR");
	        res.setMessage("Student already logged in today");
	    }

	    return res;
	}

	
	@PostMapping("/mark")
	@Operation(summary = "Mark Attendance", description = "Mark student attendance")
	@SecurityRequirement(name = "bearer-key") 
	public StudentResDTO markAttendance(@RequestBody StudentReqDTO req)
	{
		StudentDTO dto=service.markAttendance(req);
		StudentResDTO res=new StudentResDTO();
		 if (dto != null) 
		 {
		    res.setStatus("OK");
		    res.setMessage("Attendance marked successfully");
		    res.setDto(dto);
		 } 
		 else 
		 {
		    res.setStatus("ERROR");
		    res.setMessage("Unable to mark attendance");
		    res.setDto(dto);
		 }
		    return res;
	}
	

	
	@GetMapping("/getall")
	@Operation(summary = "Get All Attendance", description = "Fetch attendance of all students")
	@SecurityRequirement(name = "bearer-key") 
	public StudentRespDTO getAllAttendance()
	{
	   List<StudentDTO> dto=service.getAllAttendance();
	   StudentRespDTO res=new StudentRespDTO();
	   
	   if(dto!=null)
	   {
	    res.setStatus("OK");
	    res.setMessage("Attendance list fetched successfully");
	    res.setDto(dto);
	   }
	   else
	   {
		   res.setStatus("Error");
		   res.setMessage("No Attendance List");
		   res.setDto(null);
	   }

	    return res;
	}
	
	
	@GetMapping("/mongo/all")
	@Operation(summary = "Get All Attendance", description = "Fetch attendance of all students")
	@SecurityRequirement(name = "bearer-key") 
	public StudentRespDTO allAttendance()
	{
	   List<StudentDTO> dto=service.allAttendance();
	   StudentRespDTO res=new StudentRespDTO();
	   
	   if(dto!=null)
	   {
	    res.setStatus("OK");
	    res.setMessage("Attendance list fetched successfully");
	    res.setDto(dto);
	   }
	   else
	   {
		   res.setStatus("Error");
		   res.setMessage("No Attendance List");
		   res.setDto(null);
	   }

	    return res;
	}
	
	@GetMapping("/get/{studentId}")
	@Operation(summary = "Get Attendance by Student ID", description = "Fetch attendance records for a student")
	@SecurityRequirement(name = "bearer-key") 
	public StudentRespDTO getAttendance(@PathVariable Long studentId)
	{
		List<StudentDTO> dto=service.getAttendance(studentId);
		StudentRespDTO res=new StudentRespDTO();
		
		if(dto.isEmpty())
		{
			res.setStatus("ERROR");
	        res.setMessage("No attendance found for studentId " + studentId);
			
		}
		else
		{
			res.setStatus("ok");
			res.setMessage("Attendance fetched for studentId " + studentId);
			res.setDto(dto);
		}
		return res;
	}
	
	@DeleteMapping("/delete/{studentId}")
	@Operation(summary = "Delete Attendance", description = "Delete attendance record by student ID")
	@SecurityRequirement(name = "bearer-key") 
	public StudentResDTO deleteAttendance(@PathVariable Long studentId)
	{
		StudentDTO dto=service.deleteAttendance(studentId);
		StudentResDTO res=new StudentResDTO();
		if(dto!=null)
		{
			 res.setStatus("OK");
			 res.setMessage("Attendance deleted successfully for studentId " + studentId);
			 res.setDto(dto);
		}
		else
		{
			 res.setStatus("ERROR");
		     res.setMessage(studentId + " id not found");
		}
		return res;
	}
	
/*	@PutMapping("/update-phone")
	@Operation(summary = "Update Phone Number", description = "Update phone number using JWT token")
	public String updatePhonenumber(@RequestHeader("authorization") String authHeader,@RequestBody Map<String,String> map )
	{
		String token=authHeader.substring(7);
		String email=JwtUtil.extractEmail(token);
		String number1=map.get("number");
		
		return service.updateNumber(email,number1);
	}
	
	*/
	/*@GetMapping("/allstudents")
	@Operation(summary = "Fetch Students from Student Service", description = "Get student data from Student microservice")
    public StudentInfoResDTO getExternalStudents() 
	{
    
		List<StudentRegDTO> dto=service.getAllStudentsFromService();
		StudentInfoResDTO res=new StudentInfoResDTO();
		if(dto!=null)
		{
			res.setStatus("ok");
			res.setMessage(" Student data fetched successfully");
			res.setDto(dto);
		}
		else
		{
			res.setStatus("Error");
			res.setMessage("Student data not found");
			res.setDto(null);
		}
		return res;
    }
	*/
	/*
	@PostMapping("/check/{studentId}")
	public StudentResDTO checkAttendance(@RequestBody StudentReqDTO req,@PathVariable Long studentId)
	{
		
		StudentDTO dto=service.checkAttendance(req, studentId);
		StudentResDTO res=new StudentResDTO();
		if(dto!=null)
		{
			res.setStatus("ok");
			res.setMessage("Mark Attendance Successfully");
			res.setDto(dto);
		}
		else
		{
			res.setStatus("Error");
			res.setMessage("Student not found for Id "+studentId);
		}
		
		return res;
	}
	*/
	
	@PostMapping("/check/{studentId}")
	@Operation(summary = "Check Attendance with Circuit Breaker",
     description = "Check attendance with fallback when Student Service is down")
	@SecurityRequirement(name = "bearer-key") 
	public ResponseEntity<StudentResDTO> checkAttendance(@RequestBody StudentReqDTO req,@PathVariable Long studentId) 
	{

	    StudentDTO dto = service.checkAttendance(req, studentId);

	    StudentResDTO res = new StudentResDTO();

	    if (dto == null) 
	    {
	        res.setStatus("ERROR");
	        res.setMessage("Student not found for Id " + studentId);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
	    }

	    
	    if ("Student service is down".equalsIgnoreCase(dto.getStatus())) 
	    {

	        res.setStatus("FAILED");
	        res.setMessage("Student service unavailable");
	        res.setDto(dto);

	        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(res);
	    }

	   
	    res.setStatus("OK");
	    res.setMessage("Mark Attendance Successfully");
	    res.setDto(dto);

	    return ResponseEntity.ok(res);
	}

	
	@GetMapping("/top-attendance")
	@SecurityRequirement(name = "bearer-key") 
	public Long getTopAttendanceStudent() {
	    return service.getStudentWithHighestAttendance();
	}
	
	 @GetMapping("/allstudents")
	 @SecurityRequirement(name = "bearer-key") 
	 public ResponseEntity<StudentInfoResDTO> getAllStudents() 
	 {

	       List<StudentInfoDTO> students = service.getAllStudentsFromStudentService();

	       return ResponseEntity.ok(
	           new StudentInfoResDTO("OK","Student data fetched successfully",students));
	   }
	 
	 
	 
	 @GetMapping("/export/excel")
	 public ResponseEntity<byte[]> exportToExcel() throws Exception 
	 {

	     List<StudentDTO> list = service.getAllAttendance();

	     Workbook workbook = new XSSFWorkbook();
	     Sheet sheet = workbook.createSheet("Attendance");

	     // Header Row
	     Row header = sheet.createRow(0);
	     header.createCell(0).setCellValue("Student ID");
	     header.createCell(1).setCellValue("Date");
	     header.createCell(2).setCellValue("Login Time");
	     header.createCell(3).setCellValue("Logout Time");
	     header.createCell(4).setCellValue("Status");

	     int rowCount = 1;

	     for (StudentDTO dto : list) 
	     {
	         Row row = sheet.createRow(rowCount++);
	         row.createCell(0).setCellValue(dto.getStudentId() != null ? dto.getStudentId() : 0);
	         row.createCell(1).setCellValue(dto.getDate().toString());
	         row.createCell(2).setCellValue(dto.getLoginTime().toString());
	         row.createCell(3).setCellValue(dto.getLogoutTime() != null ? dto.getLogoutTime().toString() : "");
	         row.createCell(4).setCellValue(dto.getStatus());
	     }

	     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	     workbook.write(outputStream);
	     workbook.close();

	     byte[] excelData = outputStream.toByteArray();

	     HttpHeaders headers = new HttpHeaders();
	     headers.add("Content-Disposition", "attachment; filename=attendance.xlsx");
	     headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

	     return ResponseEntity.ok()
	             .headers(headers)
	             .body(excelData);
	 }

}
