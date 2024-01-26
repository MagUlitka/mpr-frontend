package com.example.mprprojectmvn.student.service;

import com.example.mprprojectmvn.student.resource.CreateStudent;
import com.example.mprprojectmvn.student.resource.StudentDto;
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
    List<StudentDto> getAll();

    @PostMapping
    void saveStudents(@Validated @RequestBody CreateStudent createStudent);

    @GetMapping("/{id}")
    StudentDto getStudentById(@PathVariable UUID id);

    @PostMapping("/update/{id}")
    StudentDto updateStudentById(@Validated @RequestBody StudentDto studentDto, @PathVariable UUID id);

    @DeleteMapping
    void deleteByName(String name);

    @GetMapping("/name/{name}")
    List<StudentDto> getStudentsByName(@PathVariable String name);

    @GetMapping("/surname/{surname}")
    List<StudentDto> getStudentsBySurname(@PathVariable String surname);

    @GetMapping("/courseName/{courseName}")
    List<StudentDto> getStudentsByCourseName(@PathVariable String courseName);

}
