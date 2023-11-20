package com.example.mprprojectmvn.service;

import com.example.mprprojectmvn.data.Student;
import com.example.mprprojectmvn.data.StudentRepository;
import com.example.mprprojectmvn.data.StudentUnit;
import com.example.mprprojectmvn.exceptionhandler.InvalidStudentNameException;
import com.example.mprprojectmvn.exceptionhandler.RecordNotFoundException;
import com.example.mprprojectmvn.resource.CreateStudent;
import com.example.mprprojectmvn.resource.StudentDto;
import com.example.mprprojectmvn.resource.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public Student saveStudent(CreateStudent createStudent){
        var toSave = studentMapper.toEntity(createStudent);
        var index = createIndex(createStudent.unit());
        toSave.setIndex(index);
        studentRepository.save(toSave);
        return toSave;
    }
    public StudentDto getStudentById(UUID id){
        return studentRepository.findById(id).map(studentMapper::toDto).orElseThrow(() -> new RecordNotFoundException("Student with id " + id + " not found."));
    }

    public void deleteByName(String name){
        var studentByName = studentRepository.getAllByName(name);
        if(studentByName.isEmpty()){
            throw new InvalidStudentNameException("Student with name=" + name + " does not exist.");
        }
        studentRepository.deleteAll(studentByName);
    }

    private Long createIndex(StudentUnit unit) {
        var maxIndex = studentRepository.getMaxIndex().orElse(0L);
        if(StudentUnit.GDANSK.equals(unit)){
            return 5 * maxIndex;
        }
        else {
            return 10 * maxIndex;
        }
    }

    public List<StudentDto> getStudentByName(String name) {
        return studentRepository.getFromGdanskByName(name).stream().map(studentMapper::toDto).toList();
    }
}
