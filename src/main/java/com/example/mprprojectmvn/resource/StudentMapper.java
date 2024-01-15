package com.example.mprprojectmvn.resource;

import com.example.mprprojectmvn.data.Student;
import com.example.mprprojectmvn.data.StudentUnit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper{

    public StudentDto toDto(Student student){
        return new StudentDto(student.getId(),student.getName(), student.getSurname(), student.getStudyCourseType(), student.getUnit(),student.getIndex());
    }
    public Student toEntity(CreateStudent createStudent){
        return new Student(createStudent.getName(), createStudent.getSurname(),createStudent.getStudyCourseType(), createStudent.getUnit());
    }
    public Student studentDtoToEntity(StudentDto studentDto) {
        return new Student(studentDto.id(),studentDto.name(),studentDto.surname(),studentDto.studyCourseType(),studentDto.unit(), studentDto.index());
    }
}
