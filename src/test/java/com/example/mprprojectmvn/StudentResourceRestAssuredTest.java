package com.example.mprprojectmvn;

import com.example.mprprojectmvn.data.Student;
import com.example.mprprojectmvn.data.StudentRepository;
import com.example.mprprojectmvn.data.StudentUnit;
import com.example.mprprojectmvn.data.StudyCourseType;
import com.example.mprprojectmvn.resource.CreateStudent;
import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentResourceRestAssuredTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StudentRepository repository;
    @BeforeEach
    void setUp(){
        mockMvc(mockMvc);
    }
    @AfterEach
    void cleanUp(){
        repository.deleteAll();
    }
    @Test
    void givenStudentInDbWhenGetByIdThenReturnStudentDto(){
        var student = repository.save(new Student("M", StudentUnit.GDANSK,15L));
        when().get("/students/" + student.getId())
                .then()
                .status(HttpStatus.OK)
                .body("id", equalTo(student.getId().toString()))
                .body("name",equalTo(student.getName()))
                .body("unit",equalTo(student.getUnit().toString()))
                .body("index",equalTo(student.getIndex().intValue()));
    }

    @Test
    void givenStudentDataWhenCreateStudentThenRespondIsCreated(){
        given().contentType(MediaType.APPLICATION_JSON)
                .body(new CreateStudent("Karola", "A", StudyCourseType.NEW_MEDIA_ART, StudentUnit.GDANSK,1L))
                .when()
                .post("/students")
                .then()
                .status(HttpStatus.CREATED);
    }

    @Test
    void givenStudentsInDbWhenGetByNameThenReturnList(){
        var student = repository.save(new Student("M", StudentUnit.GDANSK,15L));
        given()
                .param("name","M")
                .when().get("/students").then()
                .body("$.size()",equalTo(1))
                .body("[0].id",equalTo(student.getId().toString()))
                .body("[0].name",equalTo(student.getName()))
                .body("[0].unit",equalTo(student.getUnit().toString()))
                .body("[0].index",equalTo(student.getIndex().intValue()));
    }
}
