package com.example.mprprojectmvn.course.resource;

import com.example.mprprojectmvn.course.service.CourseService;
import com.example.mprprojectmvn.student.resource.CreateStudent;
import com.example.mprprojectmvn.student.resource.StudentDto;
import com.example.mprprojectmvn.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/courses-page")
public class CoursePageController {
    private final CourseService courseService;
    @GetMapping
    public String returnCoursesPage(Model model) {
        var courses = courseService.getAll();
        model.addAttribute("courses", courses);
        return "indexCourses";
    }

    @GetMapping("/{id}")
    public String displayGetCourseById(Model model, @PathVariable Integer id){
        model.addAttribute("course", courseService.getCourseById(id));
        return "getCourseById";
    }
    @GetMapping("/name/{name}")
    public String displayGetCourseByName(Model model, @PathVariable String name){
        var courses = courseService.getCourseByName(name);
        model.addAttribute("courses", courses);
        model.addAttribute("course", courseService.getCourseByName(name));
        return "getCourseByName";
    }
    @GetMapping("/add")
    public String displayAddCoursePage(Model model) {
        model.addAttribute("course", new CreateCourse());
        model.addAttribute("courses", courseService.getAll());
        return "addCourse";
    }

    @GetMapping("/update/{id}")
    public String displayUpdateCoursePage(Model model, @PathVariable Integer id) {
        CourseDto courseToUpdate = courseService.getCourseById(id);
        //CourseDto updatedCourse = courseService.updateCourseById(courseToUpdate,id);
        model.addAttribute("updateCourse", courseToUpdate);
        return "updateCourse";
    }

    @PostMapping
    public String saveCourse(@ModelAttribute CreateCourse createCourse){
        courseService.saveCourse(createCourse);
        return "redirect:/courses-page";
    }

    @PostMapping("/update")
    public String updateCourse(@ModelAttribute("updateCourse") CourseDto updateCourse, @RequestParam Integer id){
        courseService.updateCourseById(updateCourse, id);
        return "redirect:/courses-page";
    }
}
