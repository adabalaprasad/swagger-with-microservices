package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.demo.controllerclasses.StudentController;
import com.example.demo.entityclasses.Student;
import com.example.demo.serviceclasses.StudentService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest(StudentController.class)

class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService service;

    @Test
    void testGetAllStudents() throws Exception {

        Student s = new Student();
        s.setStudentId(1L);

        when(service.getAllStudents()).thenReturn(List.of(s));

        mockMvc.perform(get("/all"))
                .andExpect(status().isOk());
    }
}

