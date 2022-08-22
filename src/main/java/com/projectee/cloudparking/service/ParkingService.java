package com.projectee.cloudparking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.projectee.cloudparking.exception.ParkingNotFoundException;
import com.projectee.cloudparking.model.Parking;

@Service
public class ParkingService {
	private static Map<String, Parking> parkingMap = new HashMap<String, Parking>();
	
//	static {
//		String id = getUUID();
//		String id1 = getUUID();
//		Parking parking = new Parking(id, "DMS-1111", "GO", "CELTA", "CINZA");
//		Parking parking1 = new Parking(id1, "WAS-1234", "SP", "VW GOL", "ROXO");
//		parkingMap.put(id, parking);
//		parkingMap.put(id1, parking1);
//	}

	private static String getUUID() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString().replace("-","");
	}
	
	public List<Parking> findAll(){
		return parkingMap.values().stream().collect(Collectors.toList());
	}

	public Parking findById(String id) {
		// TODO Auto-generated method stub
		Parking parking = parkingMap.get(id);
		
		if(parking == null) {
			throw new ParkingNotFoundException(id);
		}
		return parking;
	}

	public Parking create(Parking parkingCreate) {
		// TODO Auto-generated method stub
		String uuid = getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingMap.put(uuid,  parkingCreate);
		return parkingCreate;
	}

	public void delete(String id) {
		// TODO Auto-generated method stub
		findById(id);
		parkingMap.remove(id);
	}

	public Parking update(String id, Parking parkingCreate) {
		// TODO Auto-generated method stub
		Parking parking = findById(id);
		parking.setColor(parkingCreate.getColor());
		parkingMap.replace(id, parking);
		return parking;
	}

	public Parking exit(String id) {
		// TODO Auto-generated method stub
		Parking parking = findById(id);
		parking.setExitDate(LocalDateTime.now());
//		var value = parking.getExitDate() - parking.getEntryDate();
		return parking;
	}
}
