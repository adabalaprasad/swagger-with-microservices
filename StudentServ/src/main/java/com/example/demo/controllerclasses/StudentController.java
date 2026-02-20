package com.example.demo.controllerclasses;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtoclasses.StudentResDTO;
import com.example.demo.dtoclasses.StudentRespDTO;
import com.example.demo.entityclasses.Student;
import com.example.demo.serviceclasses.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:2003")
@RestController
@RequestMapping("")
@Tag(name = "Student Service APIs", description = "CRUD operations for Student Service")
public class StudentController
{

	@Autowired
    private StudentService service;


	  @PostMapping("/add")
	  @Operation(summary = "Add Student", description = "Create a new student record")
	  @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Student added successfully"),
	        @ApiResponse(responseCode = "400", description = "Invalid student data")
	    })
	  public StudentResDTO addStudent(@Valid @RequestBody Student student) {

	        Student s = service.addStudent(student);
	        StudentResDTO res = new StudentResDTO();

	        if (s != null) {
	            res.setStatus("OK");
	            res.setMessage("Student data added Successfully");
	            res.setDto(s);
	        } else {
	            res.setStatus("ERROR");
	            res.setMessage("Unable to add the student data");
	        }
	        return res;
	    }

	    // ------------------ GET ALL STUDENTS ------------------
	    @GetMapping("/all")
	    @Operation(summary = "Get All Students", description = "Fetch all student records")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Students fetched successfully"),
	        @ApiResponse(responseCode = "404", description = "No students found")
	    })
	    public StudentRespDTO getAllStudents() {

	        List<Student> s = service.getAllStudents();
	        StudentRespDTO res = new StudentRespDTO();

	        if (s != null) {
	            res.setStatus("OK");
	            res.setMessage("Student list fetched successfully");
	            res.setDto(s);
	        } else {
	            res.setStatus("ERROR");
	            res.setMessage("No student list found");
	        }
	        return res;
	    }

	    // ------------------ GET STUDENT BY ID ------------------
	    @GetMapping("/{studentId}")
	    @Operation(summary = "Get Student By ID", description = "Fetch student details using student ID")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Student found"),
	        @ApiResponse(responseCode = "404", description = "Student not found")
	    })
	    public StudentResDTO getStudentById(@PathVariable Long studentId) {

	        Optional<Student> student = service.getStudentById(studentId);
	        StudentResDTO res = new StudentResDTO();

	        if (student.isPresent()) {
	            res.setStatus("OK");
	            res.setMessage("Student found");
	            res.setDto(student.get());
	        } else {
	            res.setStatus("ERROR");
	            res.setMessage("Student not found with id " + studentId);
	        }
	        return res;
	    }

	    // ------------------ UPDATE STUDENT ------------------
	    @PutMapping("/update/{studentId}")
	    @Operation(summary = "Update Student", description = "Update student details by ID")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Student updated successfully"),
	        @ApiResponse(responseCode = "404", description = "Student not found")
	    })
	    public StudentResDTO updateStudent(
	            @PathVariable Long studentId,
	            @RequestBody Student student) {

	        Student updatedStudent = service.updateStudent(studentId, student);
	        StudentResDTO res = new StudentResDTO();

	        if (updatedStudent != null) {
	            res.setStatus("OK");
	            res.setMessage("Student updated successfully");
	            res.setDto(updatedStudent);
	        } else {
	            res.setStatus("ERROR");
	            res.setMessage("Student not found with id " + studentId);
	        }
	        return res;
	    }

	    // ------------------ DELETE STUDENT ------------------
	    @DeleteMapping("/delete/{studentId}")
	    @Operation(summary = "Delete Student", description = "Delete student record by ID")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Student deleted successfully"),
	        @ApiResponse(responseCode = "404", description = "Student not found")
	    })
	    public StudentResDTO deleteStudent(@PathVariable Long studentId) {

	        boolean deleted = service.deleteStudent(studentId);
	        StudentResDTO res = new StudentResDTO();

	        if (deleted) {
	            res.setStatus("OK");
	            res.setMessage("Student deleted successfully");
	        } else {
	            res.setStatus("ERROR");
	            res.setMessage("Student not found with id " + studentId);
	        }
	        return res;
	    }
	}