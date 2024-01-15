package com.example.mprprojectmvn.resource;

import com.example.mprprojectmvn.data.Student;
import com.example.mprprojectmvn.data.StudentRepository;
import com.example.mprprojectmvn.data.StudentUnit;
import com.example.mprprojectmvn.data.StudyCourseType;
import com.example.mprprojectmvn.exceptionhandler.RecordNotFoundException;
import com.example.mprprojectmvn.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/students-page")
public class StudentPageController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;
    @GetMapping
    public String returnStudentsPage(Model model, String name) {
        model.addAttribute("name", name);
        var students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "index";
    }
    @GetMapping("/add")
    public String displayAddStudentPage(Model model) {
        model.addAttribute("student", new CreateStudent());
        return "addStudent";
    }

    @GetMapping("/update/{id}")
    public String displayUpdateStudentPage(Model model, @PathVariable UUID id) {
        Student studentToUpdate = studentRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Invalid id"));
        //AddStudent studentToAdd = new AddStudent(studentToUpdate.getId(),studentToUpdate.getName(), studentToUpdate.getSurname(), studentToUpdate.getStudyCourseType(),studentToUpdate.getUnit());
        model.addAttribute("updateStudent", studentToUpdate);
        return "updateStudent";
    }
//todo DLACZEGO saveStudent zwraca index 0???
    @PostMapping
    public String saveStudent(@ModelAttribute CreateStudent createStudent){
      CreateStudent newStudent = new CreateStudent(createStudent.getName(), createStudent.getSurname(), createStudent.getStudyCourseType(), createStudent.getUnit());
        studentService.saveStudent(newStudent);
        return "redirect:/students-page";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("updateStudent") StudentDto updateStudent, @RequestParam UUID id){
        studentService.updateStudentById(updateStudent);
        return "redirect:/students-page";
    }


}
