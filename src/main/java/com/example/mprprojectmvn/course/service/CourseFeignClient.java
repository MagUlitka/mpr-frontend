package com.example.mprprojectmvn.course.service;

import com.example.mprprojectmvn.course.resource.CourseDto;
import com.example.mprprojectmvn.course.resource.CreateCourse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name="coursesClient", url="http://localhost:8080/courses")
public interface CourseFeignClient {
    @GetMapping
    List<CourseDto> getAll();
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void saveCourses(@Validated @RequestBody CreateCourse createCourse);
    @PostMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    CourseDto updateCourseById(@Validated @RequestBody CourseDto courseDto, @PathVariable Integer id);
    @GetMapping("/{id}")
    CourseDto getCourseById(@PathVariable Integer id);
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteByName(String name);
    @GetMapping("/name/{name}")
    CourseDto getCourseByName(@PathVariable String name);
}
