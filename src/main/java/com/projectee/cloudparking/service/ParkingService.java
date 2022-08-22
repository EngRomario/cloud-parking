package com.projectee.cloudparking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.projectee.cloudparking.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import com.projectee.cloudparking.exception.ParkingNotFoundException;
import com.projectee.cloudparking.model.Parking;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ParkingService {
	private final ParkingRepository parkingRepository;

	public ParkingService(ParkingRepository parkingRepository){
		this.parkingRepository = parkingRepository;
	}
//	private static Map<String, Parking> parkingMap = new HashMap<String, Parking>();
	
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
	@Transactional(readOnly=true, propagation= Propagation.SUPPORTS)
	public List<Parking> findAll(){
		return parkingRepository.findAll();
	}
	@Transactional(readOnly=true)
	public Parking findById(String id) {
		// TODO Auto-generated method stub
		return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));

	}
	@Transactional
	public Parking create(Parking parkingCreate) {
		// TODO Auto-generated method stub
		String uuid = getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingRepository.save(parkingCreate);
		return parkingCreate;
	}
	@Transactional
	public void delete(String id) {
		// TODO Auto-generated method stub
		findById(id);
		parkingRepository.deleteById(id);
	}
	@Transactional
	public Parking update(String id, Parking parkingCreate) {
		// TODO Auto-generated method stub
		Parking parking = findById(id);
		parking.setColor(parkingCreate.getColor());
		parking.setLicense(parkingCreate.getLicense());
		parking.setModel(parkingCreate.getModel());
		parking.setState(parkingCreate.getState());
		parkingRepository.save(parking);
		return parking;
	}
	@Transactional
	public Parking checkOut(String id){
		Parking parking = findById(id);
		parking.setExitDate(LocalDateTime.now());
		parking.setBill(ParkingCheckOut.getBill(parking));
		parkingRepository.save(parking);
		return parking;
	}
}
