package com.example.mprprojectmvn.service;

import com.example.mprprojectmvn.data.Student;
import com.example.mprprojectmvn.data.StudentDataComponent;
import com.example.mprprojectmvn.data.StudentRepository;
import com.example.mprprojectmvn.data.StudentUnit;
import com.example.mprprojectmvn.resource.CreateStudent;
import com.example.mprprojectmvn.resource.StudentMapper;
import lombok.extern.java.Log;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@Log
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    private  StudentRepository studentRepository = mock(StudentRepository.class);

    private StudentMapper studentMapper = new StudentMapper();
//    @Spy
//    private StudentDataComponent studentRepository;

    private StudentService studentService;

    @BeforeEach
    void setUp(){
        //studentService = new StudentService(studentRepository,studentMapper);
    }

    @BeforeAll
    static void setUpAll(){
        log.info("Before all tests this setup is called");
    }
//    @BeforeEach
//    void setUp(){
//        log.info("Before each test this setup is called");
//    }

    @AfterEach
    void cleanUp(){
        log.info("After each test this cleanup is called");
    }

    @AfterAll
    static void cleanUpAll(){
        log.info("After all tests this cleanup is called");
    }
//    @Test
//    void givenGdanskUnitWhenSaveStudentThenGetValidIndex(){
//        //given
//        var student = new CreateStudent("Magdalena", StudentUnit.GDANSK);
////        maxIndex = 6L;
////        when(studentRepository.getMaxIndex()).thenReturn(5L); // wywoła podaną metodę dla mocka "studentRepository" (bo sam mock nie zwraca nic gdy są wywoływane metody)
//
//
//        //when
//       var savedStudent = studentService.saveStudent(student);
//
//        //then
//        assertEquals(student.name(),savedStudent.getName());
//        assertEquals(student.unit(),savedStudent.getUnit());
////        verify(studentRepository,times(1)).saveStudent(any());
//    }

//    @Test
//    void givenWarszawaUnitWhenSaveStudentThenGetValidIndex(){
//        //given
//        var student = new CreateStudent("Magdalena", StudentUnit.WARSZAWA);
//        //when
//        var savedStudent = studentService.saveStudent(student);
//
//        //then
//        assertEquals(student.name(),savedStudent.name());
//        assertEquals(student.unit(),savedStudent.unit());
//        verify(studentRepository,times(1)).saveStudent(any());
//    }
}
