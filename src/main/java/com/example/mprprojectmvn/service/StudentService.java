package com.example.mprprojectmvn.service;

import com.example.mprprojectmvn.data.Student;
import com.example.mprprojectmvn.data.StudentRepository;
import com.example.mprprojectmvn.data.StudentUnit;
import com.example.mprprojectmvn.data.StudyCourseType;
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
   // private final StudentsFeignClient feignClient;

    public Student saveStudent(CreateStudent createStudent){
        var toSave = studentMapper.toEntity(createStudent);
        var index = createIndex(createStudent.getUnit());
        toSave.setIndex(index);
        studentRepository.save(toSave);
        return toSave;
    }

//    public void saveStudent(CreateStudent createStudent){
//
////        webClient.post()
////                .bodyValue(createStudent)
//////                .body(Mono.just(createStudent), CreateStudent.class)
////                .retrieve()
////                .toBodilessEntity()
////                .subscribe(entity ->log.info("Received status " + entity.getStatusCode()));
//        restTemplate.exchange(URI.create(API_URL), HttpMethod.POST,
//                new HttpEntity<>(createStudent), Void.class);
////        webClient.post()
////                .body(Mono.just(createStudent), CreateStudent.class)
////                .retrieve().toEntityFlux(StudentDto.class).subscribe((entity) -> log.info("Received status " + entity.getStatusCode()));
//      // feignClient.saveStudents(createStudent);
//    }

    public StudentDto getStudentById(UUID id){
        try {
            return restTemplate.getForObject(API_URL + "/" + id, StudentDto.class);
        } catch (HttpClientErrorException e){
            throw new RecordNotFoundException("just to check error handling");
        }
        catch (HttpServerErrorException e){
            throw new RuntimeException();
        }
//       return feignClient.getStudentById(id);
    }

    public void deleteByName(String name){
        webClient.method(HttpMethod.DELETE)
                .retrieve()
                .toEntityFlux(StudentDto.class)
                .subscribe((entity) -> log.info("Received status: " + entity.getStatusCode()));

//        var studentByName = studentRepository.getAllByName(name);
//        if(studentByName.isEmpty()){
//            throw new InvalidStudentNameException("Student with name=" + name + " does not exist.");
//        }
//        studentRepository.deleteAll(studentByName);
    }

    public Long createIndex(StudentUnit unit) {
        var maxIndex = studentRepository.getMaxIndex().orElse(1L);
        if(maxIndex == 0) maxIndex = 1L;
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

    public List<StudentDto> getStudentsBySurname(String surname){
        var responseEntity = webClient.get().uri(uriBuilder -> uriBuilder.queryParam("surname",surname).build())
                .retrieve()
                .toEntityFlux(StudentDto.class)
                .block();
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            return  responseEntity.getBody().toStream().toList();
        }
        else if(responseEntity.getStatusCode().is4xxClientError()){
            throw new RecordNotFoundException("No students with given surname found");
        }
        else throw new RuntimeException();
       // return studentRepository.getStudentsBySurname(surname).stream().map(studentMapper::toDto).toList();
    }
    public List<StudentDto> getStudentsByStudyCourseType(StudyCourseType studyCourseType){
        var responseEntity = webClient.get().uri(uriBuilder -> uriBuilder.queryParam("studyCourseType", studyCourseType).build())
                .retrieve()
                .toEntityFlux(StudentDto.class)
                .block();
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            return  responseEntity.getBody().toStream().toList();
        }
        else if(responseEntity.getStatusCode().is4xxClientError()){
            throw new RecordNotFoundException("No students with given studyCourseType found");
        }
        else throw new RuntimeException();
        //return studentRepository.getStudentsByStudyCourseType(studyCourseType).stream().map(studentMapper::toDto).toList();
    }

    public Student updateStudentById(StudentDto studentDto){
        Student toUpdate = studentRepository.findById(studentDto.id()).orElseThrow(() -> new RecordNotFoundException("There's no such student in the database"));
       //studentMapper.studentDtoToEntity(studentDto);
       toUpdate.setName(studentDto.name());
       toUpdate.setSurname(studentDto.surname());
       toUpdate.setStudyCourseType(studentDto.studyCourseType());
       toUpdate.setUnit(studentDto.unit());
        return studentRepository.save(toUpdate);
    }
}
