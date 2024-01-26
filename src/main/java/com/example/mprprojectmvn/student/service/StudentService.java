package com.example.mprprojectmvn.student.service;

import com.example.mprprojectmvn.student.resource.CreateStudent;
import com.example.mprprojectmvn.student.resource.StudentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log
@Service
@RequiredArgsConstructor
@EnableFeignClients
public class StudentService{

    private static final String API_URL = "http://localhost:8080/students";

   // private final StudentMapper studentMapper;
   // private final RestTemplate restTemplate = new RestTemplate();
//    private final WebClient webClient = WebClient.builder()
//            .baseUrl(API_URL)
//            .build();

   private final StudentsFeignClient feignClient;

    public List<StudentDto> getAll(){
       return feignClient.getAll();
    }

    public void saveStudent(CreateStudent createStudent){

//        webClient.post()
//                .bodyValue(createStudent)
////                .body(Mono.just(createStudent), CreateStudent.class)
//                .retrieve()
//                .toBodilessEntity()
//                .subscribe(entity ->log.info("Received status " + entity.getStatusCode()));
//        restTemplate.exchange(URI.create(API_URL), HttpMethod.POST,
//                new HttpEntity<>(createStudent), Void.class);
//        webClient.post()
//                .body(Mono.just(createStudent), CreateStudent.class)
//                .retrieve().toEntityFlux(StudentDto.class).subscribe((entity) -> log.info("Received status " + entity.getStatusCode()));
       feignClient.saveStudents(createStudent);
    }

    public StudentDto getStudentById(UUID id){
//        try {
//            return restTemplate.getForObject(API_URL + "/" + id, StudentDto.class);
//        } catch (HttpClientErrorException e){
//            throw new RecordNotFoundException("just to check error handling");
//        }
//        catch (HttpServerErrorException e){
//            throw new RuntimeException();
//        }
      return feignClient.getStudentById(id);
    }

    public void deleteByName(String name){
//        webClient.method(HttpMethod.DELETE)
//                .retrieve()
//                .toEntityFlux(StudentDto.class)
//                .subscribe((entity) -> log.info("Received status: " + entity.getStatusCode()));

//        var studentByName = studentRepository.getAllByName(name);
//        if(studentByName.isEmpty()){
//            throw new InvalidStudentNameException("Student with name=" + name + " does not exist.");
//        }
//        studentRepository.deleteAll(studentByName);
        feignClient.deleteByName(name);
    }

    public List<StudentDto> getStudentsByName(String name) {
//        var responseEntity = webClient.get().uri(uriBuilder -> uriBuilder.queryParam("name",name).build())
//                .retrieve()
//                .toEntityFlux(StudentDto.class)
//                .block();
//        if(responseEntity.getStatusCode().is2xxSuccessful()){
//            return responseEntity.getBody().toStream().toList();
//        }
//        else if (responseEntity.getStatusCode().is4xxClientError()){
//            throw new RecordNotFoundException("just to check error handling");
//        }
//        throw new RuntimeException();
        return feignClient.getStudentsByName(name);
    }

    public List<StudentDto> getStudentsBySurname(String surname){
//        var responseEntity = webClient.get().uri(uriBuilder -> uriBuilder.queryParam("surname",surname).build())
//                .retrieve()
//                .toEntityFlux(StudentDto.class)
//                .block();
//        if(responseEntity.getStatusCode().is2xxSuccessful()){
//            return  responseEntity.getBody().toStream().toList();
//        }
//        else if(responseEntity.getStatusCode().is4xxClientError()){
//            throw new RecordNotFoundException("No students with given surname found");
//        }
//        else throw new RuntimeException();
//       // return studentRepository.getStudentsBySurname(surname).stream().map(studentMapper::toDto).toList();
        return feignClient.getStudentsBySurname(surname);
    }
    public List<StudentDto> getStudentsByCourseName(String courseName){
//        var responseEntity = webClient.get().uri(uriBuilder -> uriBuilder.queryParam("studyCourseType", studyCourseType).build())
//                .retrieve()
//                .toEntityFlux(StudentDto.class)
//                .block();
//        if(responseEntity.getStatusCode().is2xxSuccessful()){
//            return  responseEntity.getBody().toStream().toList();
//        }
//        else if(responseEntity.getStatusCode().is4xxClientError()){
//            throw new RecordNotFoundException("No students with given studyCourseType found");
//        }
//        else throw new RuntimeException();
        //return studentRepository.getStudentsByStudyCourseType(studyCourseType).stream().map(studentMapper::toDto).toList();
        return feignClient.getStudentsByCourseName(courseName);
    }

    public StudentDto updateStudentById(StudentDto studentDto, UUID id){
        return feignClient.updateStudentById(studentDto, id);
    }
}
