package com.example.mprprojectmvn.resource;

import com.example.mprprojectmvn.data.Student;
import com.example.mprprojectmvn.data.StudyCourseType;
import com.example.mprprojectmvn.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/students")
@RequiredArgsConstructor
public class StudentResource {
    private final StudentService studentService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudents(@Validated @RequestBody CreateStudent createStudent){
        studentService.saveStudent(createStudent);
    }

    @PostMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudentById(@Validated @RequestBody StudentDto studentDto, @PathVariable UUID id){
        return studentService.updateStudentById(studentDto);
    }
    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable UUID id){
        return studentService.getStudentById(id);

    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByName(String name){
        studentService.deleteByName(name);
    }

    @GetMapping("/show/{name}")
    public List<StudentDto> getStudentByName(@PathVariable String name){
        return studentService.getStudentByName(name);
    }

    @GetMapping("/surname")
    public List<StudentDto> getStudentBySurname(@RequestParam String surname){
        return studentService.getStudentsBySurname(surname);
    }

    @GetMapping("/studyCourseType")
    public List<StudentDto> getStudentByStudyCourseType(@RequestParam StudyCourseType studyCourseType){
        return studentService.getStudentsByStudyCourseType(studyCourseType);
    }
}
