package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import com.example.demo.entityclasses.Student;
import com.example.demo.entityclasses.StudentMongo;
import com.example.demo.repositories.StudentMongoRepo;
import com.example.demo.repositories.StudentRepo;
import com.example.demo.serviceclasses.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest 
{

    @Mock
    private StudentRepo repo;

    @Mock
    private StudentMongoRepo mongoRepo;

    @InjectMocks
    private StudentService service;

    @Test
    void testAddStudent() {

        Student student = new Student();
        student.setStudentId(1L);
        student.setStudentName("Prasad");

        when(repo.save(student)).thenReturn(student);

        Student saved = service.addStudent(student);

        assertNotNull(saved);
        assertEquals("Prasad", saved.getStudentName());

        verify(repo, times(1)).save(student);
        verify(mongoRepo, times(1)).save(any(StudentMongo.class));
    }

    @Test
    void testGetAllStudents() {

        Student s1 = new Student();
        s1.setStudentId(1L);

        when(repo.findAll()).thenReturn(List.of(s1));

        List<Student> list = service.getAllStudents();

        assertEquals(1, list.size());
    }

    @Test
    void testGetStudentById() {

        Student s = new Student();
        s.setStudentId(1L);

        when(repo.findByStudentId(1L)).thenReturn(Optional.of(s));

        Optional<Student> result = service.getStudentById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void testDeleteStudent() {

        when(repo.findByStudentId(1L)).thenReturn(Optional.of(new Student()));

        boolean result = service.deleteStudent(1L);

        assertTrue(result);
        verify(repo).deleteByStudentId(1L);
    }
}

