package com.projectee.cloudparking.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.projectee.cloudparking.controller.dto.ParkingCreateDTO;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerIT {

	@LocalServerPort
	private int randomPort;
	
	@BeforeEach
	public void setUpTest() {
		RestAssured.port = randomPort;
	}
	
	@Test
	void whenFindAllCheckStatus() {
		RestAssured.given()
			.when()
			.get("/parking")
			.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	void whenCreateThenCheckIsCreated() {
		ParkingCreateDTO parkingCreateDTO = new ParkingCreateDTO(); 
		parkingCreateDTO.setColor("AMARELO");
		parkingCreateDTO.setLicense("AMZ-9999");
		parkingCreateDTO.setModel("Brasilia");
		parkingCreateDTO.setState("AM");
		
		RestAssured.given()
		.when()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.body(parkingCreateDTO)
		.post("/parking")
		.then()
		.statusCode(HttpStatus.CREATED.value())
		.body("license", Matchers.equalTo("AMZ-9999"))
		.body("color", Matchers.equalTo("AMARELO"))
		.body("model", Matchers.equalTo("Brasilia"))
		.body("state", Matchers.equalTo("AM"));
	}

}
