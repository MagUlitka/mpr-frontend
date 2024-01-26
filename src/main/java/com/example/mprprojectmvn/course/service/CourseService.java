package com.example.mprprojectmvn.course.service;

import com.example.mprprojectmvn.course.resource.CourseDto;
import com.example.mprprojectmvn.course.resource.CreateCourse;
import com.example.mprprojectmvn.student.resource.CreateStudent;
import com.example.mprprojectmvn.student.resource.StudentDto;
import com.example.mprprojectmvn.student.service.StudentsFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@EnableFeignClients
public class CourseService {
    private static final String API_URL = "http://localhost:8080/courses";

    private final CourseFeignClient feignClient;

    public List<CourseDto> getAll(){
        return feignClient.getAll();
    }

    public void saveCourse(CreateCourse createCourse){
        feignClient.saveCourses(createCourse);
    }
    public CourseDto getCourseById(Integer id){
        return feignClient.getCourseById(id);
    }

    public void deleteByName(String name){
        feignClient.deleteByName(name);
    }

    public CourseDto getCourseByName(String name) {
        return feignClient.getCourseByName(name);
    }

    public CourseDto updateCourseById(CourseDto courseDto, Integer id){
        return feignClient.updateCourseById(courseDto,id);
    }
}
