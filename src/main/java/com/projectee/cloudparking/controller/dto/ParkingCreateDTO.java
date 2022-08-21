package com.projectee.cloudparking.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingCreateDTO {
	private String license;
	private String state;
	private String model;
	private String color;
}
