package com.example.mprprojectmvn.service;

import com.example.mprprojectmvn.resource.CreateStudent;
import com.example.mprprojectmvn.resource.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name="studentsClient", url="http://localhost:8080/students")
public interface StudentsFeignClient {
    @PostMapping
    void saveStudents(@Validated @RequestBody CreateStudent createStudent);
    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable UUID id);
}
