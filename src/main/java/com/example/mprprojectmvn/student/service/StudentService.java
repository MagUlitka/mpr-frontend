package com.example.mprprojectmvn.student.service;

import com.example.mprprojectmvn.student.resource.CreateStudent;
import com.example.mprprojectmvn.student.resource.StudentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log
@Service
@RequiredArgsConstructor
@EnableFeignClients
public class StudentService{

    private static final String API_URL = "http://localhost:8080/students";

   private final StudentsFeignClient feignClient;

    public List<StudentDto> getAll(){
       return feignClient.getAll();
    }

    public void saveStudent(CreateStudent createStudent){
       feignClient.saveStudents(createStudent);
    }

    public StudentDto getStudentById(UUID id){
      return feignClient.getStudentById(id);
    }

    public void deleteByName(String name){
        feignClient.deleteByName(name);
    }

    public List<StudentDto> getStudentsByName(String name) {
        return feignClient.getStudentsByName(name);
    }

    public List<StudentDto> getStudentsBySurname(String surname){
        return feignClient.getStudentsBySurname(surname);
    }
    public List<StudentDto> getStudentsByCourseName(String courseName){
        return feignClient.getStudentsByCourseName(courseName);
    }

    public StudentDto updateStudentById(StudentDto studentDto, UUID id){
        return feignClient.updateStudentById(studentDto, id);
    }
}
