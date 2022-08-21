package com.projectee.cloudparking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.projectee.cloudparking.model.Parking;

@Service
public class ParkingService {
	private static Map<String, Parking> parkingMap = new HashMap<String, Parking>();
	
	static {
		var id = getUUID();
		Parking parking = new Parking(id, "DMS-1111", "GO", "CELTA", "CINZA");
		parkingMap.put(id, parking);
	}

	private static String getUUID() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString().replace("-","");
	}
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList());
	}
}