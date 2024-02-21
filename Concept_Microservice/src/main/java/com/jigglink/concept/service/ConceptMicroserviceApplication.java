package com.jigglink.concept.service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "Concept microservice", description = "Process the information related to " +
		"the Jigglink student itineray Concept; It is able to connect to idea microservice so a specific student itinerary concept is able to manage its own study ideas.", version = "1.0.0"))
public class ConceptMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConceptMicroserviceApplication.class, args);
	}

}
