package com.example.mprprojectmvn.resource;

import com.example.mprprojectmvn.data.Student;
import com.example.mprprojectmvn.data.StudentUnit;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDto toDto(Student student){
        return new StudentDto(student.getId(),student.getName(),student.getUnit(),student.getIndex());
    }
    public Student toEntity(CreateStudent createStudent){
        return new Student(createStudent.name(), createStudent.unit());
    }
}
