package com.jigglink.itinerary.service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "Itinerary microservice", description = "Process the information related to " +
		"the Jigglink student Itinerary; It is able to connect to Concept microservice so a specific student's Itinerary is able to manage its own concepts.", version = "1.0.0"))
public class ItineraryMicroserviceApplication {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(ItineraryMicroserviceApplication.class, args);
	}

}
