package com.example.mprprojectmvn.service;

import com.example.mprprojectmvn.data.Student;
import com.example.mprprojectmvn.data.StudyCourseType;
import com.example.mprprojectmvn.resource.CreateStudent;
import com.example.mprprojectmvn.resource.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Component
@FeignClient(name="studentsClient", url="http://localhost:8080/students")
public interface StudentsFeignClient {

    @GetMapping
    List<Student> getAll();
    @PostMapping
    void saveStudents(@Validated @RequestBody CreateStudent createStudent);
    @GetMapping("/{id}")
    StudentDto getStudentById(@PathVariable UUID id);
    @PostMapping("/update/{id}")
    Student updateStudentById(@Validated @RequestBody StudentDto studentDto, @PathVariable UUID id);
    @DeleteMapping
    void deleteByName(String name);
    @GetMapping("/name/{name}")
    List<StudentDto> getStudentsByName(@PathVariable String name);

    @GetMapping("/surname/{surname}")
    List<StudentDto> getStudentsBySurname(@PathVariable String surname);

    @GetMapping("/studyCourseType/{studyCourseType}")
    List<StudentDto> getStudentsByStudyCourseType(@PathVariable StudyCourseType studyCourseType);
}
