package com.example.mprprojectmvn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MprProjectMvnApplication {

	public static void main(String[] args) {
		SpringApplication.run(MprProjectMvnApplication.class, args);
	}

}
