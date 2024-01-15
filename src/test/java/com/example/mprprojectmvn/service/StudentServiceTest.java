package com.example.mprprojectmvn.service;

import com.example.mprprojectmvn.data.*;
import com.example.mprprojectmvn.exceptionhandler.RecordNotFoundException;
import com.example.mprprojectmvn.resource.CreateStudent;
import com.example.mprprojectmvn.resource.StudentDto;
import com.example.mprprojectmvn.resource.StudentMapper;
import lombok.extern.java.Log;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@Log
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    private StudentRepository studentRepository = mock(StudentRepository.class);

    private StudentMapper studentMapper = new StudentMapper();

//    @Spy
//    private StudentDataComponent studentRepository;

    private StudentService studentService;

    @BeforeEach
    void setUp(){
        studentService = new StudentService(studentRepository,studentMapper);
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
    @Test
    void givenGdanskUnitWhenSaveStudentThenGetValidIndex(){
        //given
        var student = new CreateStudent("Magdalena", "C",StudyCourseType.COMPUTER_SCIENCE,StudentUnit.GDANSK);
//        maxIndex = 6L;
//        when(studentRepository.getMaxIndex()).thenReturn(5L); // wywoła podaną metodę dla mocka "studentRepository" (bo sam mock nie zwraca nic gdy są wywoływane metody)


        //when
       var savedStudent = studentService.saveStudent(student);

        //then
        assertEquals(student.getName(),savedStudent.getName());
        assertEquals(student.getUnit(),savedStudent.getUnit());
//        verify(studentRepository,times(1)).saveStudent(any());
    }

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

    @Test
    void givenExistingSurnameWhenGetStudentsBySurnameThenReturnListWithStudents(){
        Student student1 = new Student(UUID.randomUUID(), "M", "Kowal", StudyCourseType.NEW_MEDIA_ART, StudentUnit.GDANSK, 0L);
        Student student2 = new Student(UUID.randomUUID(), "S", "Ziel", StudyCourseType.NEW_MEDIA_ART, StudentUnit.GDANSK, 5L);
        Student student3 = new Student(UUID.randomUUID(), "W", "Kowal", StudyCourseType.NEW_MEDIA_ART, StudentUnit.GDANSK, 10L);
        List<Student> predestinedMatches = Arrays.asList(student1, student3);

        when(studentRepository.getStudentsBySurname("Kowal")).thenReturn(predestinedMatches);

        List<StudentDto> foundStudents = studentService.getStudentsBySurname("Kowal");

        assertEquals(predestinedMatches.stream().map(studentMapper::toDto).toList(),foundStudents);
    }

    @Test
    void givenNonExistingSurnameWhenGetStudentsBySurnameThenReturnEmptyList(){
        Student student1 = new Student(UUID.randomUUID(), "M", "Kowal", StudyCourseType.NEW_MEDIA_ART, StudentUnit.GDANSK, 0L);
        Student student2 = new Student(UUID.randomUUID(), "S", "Ziel", StudyCourseType.NEW_MEDIA_ART, StudentUnit.GDANSK, 5L);
        Student student3 = new Student(UUID.randomUUID(), "W", "Kowal", StudyCourseType.NEW_MEDIA_ART, StudentUnit.GDANSK, 10L);
        List<Student> predestinedMatches = new ArrayList<>();

        when(studentRepository.getStudentsBySurname("Nowak")).thenReturn(predestinedMatches);

        List<StudentDto> foundStudents = studentService.getStudentsBySurname("Nowak");

        assertEquals(predestinedMatches.stream().map(studentMapper::toDto).toList(),foundStudents);
    }

    @Test
    void givenExistingStudyCourseTypeWhenGetStudentsByStudyCourseTypeThenReturnListWithStudents(){
        Student student1 = new Student(UUID.randomUUID(), "M", "Kowal", StudyCourseType.NEW_MEDIA_ART, StudentUnit.GDANSK, 0L);
        Student student2 = new Student(UUID.randomUUID(), "S", "Ziel", StudyCourseType.COMPUTER_SCIENCE, StudentUnit.GDANSK, 5L);
        List<Student> predestinedMatches = Arrays.asList(student2);

        when(studentRepository.getStudentsByStudyCourseType(StudyCourseType.COMPUTER_SCIENCE)).thenReturn(predestinedMatches);

        List<StudentDto> foundStudents = studentService.getStudentsByStudyCourseType(StudyCourseType.COMPUTER_SCIENCE);

        assertEquals(predestinedMatches.stream().map(studentMapper::toDto).toList(),foundStudents);
    }
    @Test
    void givenExistingStudentIdAndUpdateDtoWhenUpdateStudentByIdThenUpdateAndReturnStudent() {
        Student existingStudent = new Student(UUID.randomUUID(), "M", "K", StudyCourseType.NEW_MEDIA_ART, StudentUnit.GDANSK, 0L);
        StudentDto updatedStudentDto = new StudentDto(existingStudent.getId(),"Magdalena","C", StudyCourseType.COMPUTER_SCIENCE,StudentUnit.GDANSK, 5L);

        when(studentRepository.findById(eq(existingStudent.getId()))).thenReturn(Optional.of(existingStudent));
        studentMapper.studentDtoToEntity(updatedStudentDto);
        when(studentRepository.save(eq(existingStudent))).thenReturn(existingStudent);

        Student updatedStudent = studentService.updateStudentById(updatedStudentDto);

        assertEquals(existingStudent, updatedStudent);
        verify(studentRepository, times(1)).findById(eq(existingStudent.getId()));
        verify(studentRepository, times(1)).save(eq(existingStudent));
    }

    @Test
    void givenNonExistingStudentIdWhenUpdateStudentByIdThenThrowRecordNotFoundException() {

        StudentDto updatedStudentDto = new StudentDto(UUID.randomUUID(),"Magdalena","C", StudyCourseType.COMPUTER_SCIENCE,StudentUnit.GDANSK, 5L);

        when(studentRepository.findById(eq(updatedStudentDto.id()))).thenReturn(Optional.empty());

        RecordNotFoundException exception = assertThrows(RecordNotFoundException.class, () -> {
            studentService.updateStudentById(updatedStudentDto);
        });

        assertEquals("There's no such student in the database", exception.getMessage());
        verify(studentRepository, times(1)).findById(eq(updatedStudentDto.id()));
        verify(studentRepository, never()).save(any());
    }

}
