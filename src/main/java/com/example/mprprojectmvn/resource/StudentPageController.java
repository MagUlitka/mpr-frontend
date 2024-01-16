package com.example.mprprojectmvn.resource;



import com.example.mprprojectmvn.data.Student;
import com.example.mprprojectmvn.data.StudyCourseType;
import com.example.mprprojectmvn.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/students-page")
public class StudentPageController {

    private final StudentService studentService;
    @GetMapping
    public String returnStudentsPage(Model model, String name) {
        model.addAttribute("name", name);
        var students = studentService.getAll();
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/{id}")
    public String displayGetStudentById(Model model, @PathVariable UUID id){
        model.addAttribute("student",studentService.getStudentById(id));
        return "getStudentById";
    }
    @GetMapping("/name/{name}")
    public String displayGetStudentsByName(Model model, @PathVariable String name){
        var students = studentService.getStudentsByName(name);
        model.addAttribute("students", students);
        model.addAttribute("student",studentService.getStudentsByName(name));
        return "getStudentsByName";
    }

    @GetMapping("/surname/{surname}")
    public String displayGetStudentsBySurname(Model model, @PathVariable String surname){
        var students = studentService.getStudentsBySurname(surname);
        model.addAttribute("students", students);
        model.addAttribute("student",studentService.getStudentsBySurname(surname));
        return "getStudentsBySurname";
    }

    @GetMapping("/studyCourseType/{studyCourseType}")
    public String displayGetStudentsByStudyCourseType(Model model, @PathVariable StudyCourseType studyCourseType){
        var students = studentService.getStudentsByStudyCourseType(studyCourseType);
        model.addAttribute("students", students);
        model.addAttribute("student",studentService.getStudentsByStudyCourseType(studyCourseType));
        return "getStudentsByStudyCourseType";
    }
    @GetMapping("/add")
    public String displayAddStudentPage(Model model) {
        model.addAttribute("student", new CreateStudent());
        return "addStudent";
    }

    @GetMapping("/update/{id}")
    public String displayUpdateStudentPage(Model model, @PathVariable UUID id) {
        StudentDto studentToUpdate = studentService.getStudentById(id);
        Student updatedStudent = studentService.updateStudentById(studentToUpdate,id);
        model.addAttribute("updateStudent", updatedStudent);
        return "updateStudent";
    }

    @PostMapping
    public String saveStudent(@ModelAttribute CreateStudent createStudent){
      CreateStudent newStudent = new CreateStudent(createStudent.getName(), createStudent.getSurname(), createStudent.getStudyCourseType(), createStudent.getUnit());
        studentService.saveStudent(newStudent);
        return "redirect:/students-page";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("updateStudent") StudentDto updateStudent, @RequestParam UUID id){
        studentService.updateStudentById(updateStudent, id);
        return "redirect:/students-page";
    }


}
