package com.example.mprprojectmvn;

import com.example.mprprojectmvn.exceptionhandler.RecordNotFoundException;
import com.example.mprprojectmvn.resource.StudentResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class MprProjectMvnApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private StudentResource studentResource;
	@Test
	void contextLoads() {
		assertThrows(RecordNotFoundException.class, () -> studentResource.getStudentById(UUID.randomUUID()));
	}

	@Test
	void givenNoStudents_whenGetById_thenRespondWithNotFound() throws Exception{
		var response = mockMvc.perform(get("/students/ad63cd7a-59bf-4a28-9d1f-6a8a4ebd2328")).andDo(print()).andReturn().getResponse();
		assertEquals(response.getStatus(), 404);
		assertTrue(response.getContentAsString().contains("not found"));
	}
}
