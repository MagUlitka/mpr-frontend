package com.example.mprprojectmvn;


import static org.assertj.core.api.BDDAssertions.then;

//@SpringBootTest
//@AutoConfigureMockMvc
//public class StudentResourceRestAssuredTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private StudentRepository repository;
//    @BeforeEach
//    void setUp(){
//        mockMvc(mockMvc);
//    }
//    @AfterEach
//    void cleanUp(){
//        repository.deleteAll();
//    }
//    @Test
//    void givenStudentInDbWhenGetByIdThenReturnStudentDto(){
//        var student = repository.save(new Student("M", StudentUnit.GDANSK,15L));
//        when().get("/students/" + student.getId())
//                .then()
//                .status(HttpStatus.OK)
//                .body("id", equalTo(student.getId().toString()))
//                .body("name",equalTo(student.getName()))
//                .body("unit",equalTo(student.getUnit().toString()))
//                .body("index",equalTo(student.getIndex().intValue()));
//    }
//
//    @Test
//    void givenStudentDataWhenCreateStudentThenRespondIsCreated(){
//        given().contentType(MediaType.APPLICATION_JSON)
//                .body(new CreateStudent("Karola", "A", StudyCourseType.NEW_MEDIA_ART, StudentUnit.GDANSK))
//                .when()
//                .post("/students")
//                .then()
//                .status(HttpStatus.CREATED);
//    }
//
//    @Test
//    void givenStudentsInDbWhenGetByNameThenReturnList(){
//        var student = repository.save(new Student("M", StudentUnit.GDANSK,15L));
//        given()
//                .param("name","M")
//                .when().get("/students").then()
//                .body("$.size()",equalTo(1))
//                .body("[0].id",equalTo(student.getId().toString()))
//                .body("[0].name",equalTo(student.getName()))
//                .body("[0].unit",equalTo(student.getUnit().toString()))
//                .body("[0].index",equalTo(student.getIndex().intValue()));
//    }
//}
