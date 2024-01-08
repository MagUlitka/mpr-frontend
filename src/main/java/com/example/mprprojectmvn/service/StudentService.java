package com.example.mprprojectmvn.service;

import com.example.mprprojectmvn.data.Student;
import com.example.mprprojectmvn.data.StudentRepository;
import com.example.mprprojectmvn.data.StudentUnit;
import com.example.mprprojectmvn.exceptionhandler.InvalidStudentNameException;
import com.example.mprprojectmvn.exceptionhandler.RecordNotFoundException;
import com.example.mprprojectmvn.resource.CreateStudent;
import com.example.mprprojectmvn.resource.StudentDto;
import com.example.mprprojectmvn.resource.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Log
@Service
@RequiredArgsConstructor
public class StudentService {

    private static final String API_URL = "http://localhost:8080/students";

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    private final RestTemplate restTemplate = new RestTemplate();
    private final WebClient webClient = WebClient.builder()
            .baseUrl(API_URL)
            .build();
    private final StudentsFeignClient feignClient;


    public void saveStudent(CreateStudent createStudent){
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
        var studentByName = studentRepository.getAllByName(name);
        if(studentByName.isEmpty()){
            throw new InvalidStudentNameException("Student with name=" + name + " does not exist.");
        }
        studentRepository.deleteAll(studentByName);
    }

    private Long createIndex(StudentUnit unit) {
        var maxIndex = studentRepository.getMaxIndex().orElse(0L);
        if(StudentUnit.GDANSK.equals(unit)){
            return 5 * maxIndex;
        }
        else {
            return 10 * maxIndex;
        }
    }

    public List<StudentDto> getStudentByName(String name) {
        var responseEntity = webClient.get().uri(uriBuilder -> uriBuilder.queryParam("name",name).build())
                .retrieve()
                .toEntityFlux(StudentDto.class)
                .block();
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            return responseEntity.getBody().toStream().toList();
        }
        else if (responseEntity.getStatusCode().is4xxClientError()){
            throw new RecordNotFoundException("just to check error handling");
        }
        throw new RuntimeException();
    }
}
