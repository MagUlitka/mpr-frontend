package com.example.mprprojectmvn.resource;

import com.example.mprprojectmvn.data.Student;
import com.example.mprprojectmvn.data.StudentRepository;
import com.example.mprprojectmvn.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("student", new AddStudent());

        return "addStudent";
    }

    @PostMapping
    public String  saveStudent(@ModelAttribute AddStudent addStudent){
        studentService.saveStudent(new CreateStudent(addStudent.getName(), addStudent.getUnit()));
        return "redirect:/students-page";
    }
}
