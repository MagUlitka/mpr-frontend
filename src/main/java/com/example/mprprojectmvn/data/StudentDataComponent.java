package com.example.mprprojectmvn.data;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class StudentDataComponent {
    private final List<Student> students = new ArrayList<>();
    public StudentDataComponent() {
        var student = new Student(UUID.fromString("6f16f034-de79-47ad-b05f-77ec29b7a453"),"Magdalena","C", StudyCourseType.COMPUTER_SCIENCE, StudentUnit.GDANSK, 1L);
        students.add(student);
    }

    public void saveStudent(Student student){
        students.add(student);
    }
    public Student getStudentById(UUID id){
        return students.stream()
                .filter(it -> it.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Long getMaxIndex(){
        return students.stream()
                .map(Student::getIndex)
                .max(Comparator.naturalOrder())
                .orElse(0L);
    }
}
