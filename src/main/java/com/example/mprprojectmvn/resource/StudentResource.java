package com.example.mprprojectmvn.resource;

import com.example.mprprojectmvn.data.Student;
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
    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable UUID id){
        return studentService.getStudentById(id);

    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByName(String name){
        studentService.deleteByName(name);
    }

    @GetMapping
    public List<StudentDto> getStudentByName(@RequestParam String name){
        return studentService.getStudentByName(name);
    }
}
