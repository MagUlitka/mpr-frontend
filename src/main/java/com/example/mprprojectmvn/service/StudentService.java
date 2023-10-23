package com.example.mprprojectmvn.service;

import com.example.mprprojectmvn.data.Student;
import com.example.mprprojectmvn.data.StudentDataComponent;
import com.example.mprprojectmvn.data.StudentRepository;
import com.example.mprprojectmvn.data.StudentUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student saveStudent(Student student){
        var index = createIndex(student.getUnit());
        var toSave = new Student(student.getName(), student.getUnit(), index);
        studentRepository.save(toSave);
        return toSave;
    }
    public Student getStudentById(UUID id){
        return studentRepository.findById(id).orElseThrow();
    }

    public void deleteByName(String name){
        studentRepository.deleteByName(name);
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
}
