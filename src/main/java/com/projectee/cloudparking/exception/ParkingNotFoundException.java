package com.projectee.cloudparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ParkingNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8950156626984229009L;

	public ParkingNotFoundException(String id) {
		// TODO Auto-generated constructor stub
		super("Parking not found with ID: " + id);
	}

}
