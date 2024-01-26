package com.example.mprprojectmvn.student.resource;



import com.example.mprprojectmvn.course.service.CourseService;
import com.example.mprprojectmvn.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/students-page")
public class StudentPageController {

    private final StudentService studentService;
    private final CourseService courseService;
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

    @GetMapping("/courseName/{courseName}")
    public String displayGetStudentsByCourseName(Model model, @PathVariable String courseName){
        var students = studentService.getStudentsByCourseName(courseName);
        model.addAttribute("students", students);
        model.addAttribute("student",studentService.getStudentsByCourseName(courseName));
        return "getStudentsByCourseName";
    }
    @GetMapping("/add")
    public String displayAddStudentPage(Model model) {
        model.addAttribute("student", new CreateStudent());
        model.addAttribute("courses", courseService.getAll());
        return "addStudent";
    }

    @GetMapping("/update/{id}")
    public String displayUpdateStudentPage(Model model, @PathVariable UUID id) {
        StudentDto studentToUpdate = studentService.getStudentById(id);
        StudentDto updatedStudent = studentService.updateStudentById(studentToUpdate,id);
        model.addAttribute("updateStudent", updatedStudent);
        model.addAttribute("courses", courseService.getAll());
        return "updateStudent";
    }

    @PostMapping
    public String saveStudent(@ModelAttribute CreateStudent createStudent){
      CreateStudent newStudent = new CreateStudent(createStudent.getName(), createStudent.getSurname(), createStudent.getCourseName(), createStudent.getUnit());
        studentService.saveStudent(newStudent);
        return "redirect:/students-page";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("updateStudent") StudentDto updateStudent, @RequestParam UUID id){
        studentService.updateStudentById(updateStudent, id);
        return "redirect:/students-page";
    }


}
