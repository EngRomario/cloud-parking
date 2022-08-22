package com.projectee.cloudparking.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parking {
	@Id
	private String id;
	private String license;
	private String state;
	private String model;
	private String color;
	private LocalDateTime entryDate;
	private LocalDateTime exitDate;
	private Double bill;
	
	public Parking(String id, String license, String state, String model, String color) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.license = license;
		this.state = state;
		this.model = model;
		this.color = color;
		
	}
}
