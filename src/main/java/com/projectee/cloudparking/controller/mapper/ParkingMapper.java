package com.projectee.cloudparking.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.projectee.cloudparking.controller.dto.ParkingCreateDTO;
import com.projectee.cloudparking.controller.dto.ParkingDTO;
import com.projectee.cloudparking.model.Parking;

@Component
public class ParkingMapper {
	private static final ModelMapper MODEL_MAPPER = new ModelMapper();
	
	
	public ParkingDTO toParkingDTO(Parking parking) {
		return MODEL_MAPPER.map(parking, ParkingDTO.class);
	}

	public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
		// TODO Auto-generated method stub
		return parkingList.stream().map(this::toParkingDTO).collect(Collectors.toList());
	}

	public Parking toParking(ParkingDTO dto) {
		// TODO Auto-generated method stub
		return MODEL_MAPPER.map(dto, Parking.class);
	}

	public Parking toParkingCreate(ParkingCreateDTO dto) {
		// TODO Auto-generated method stub
		return MODEL_MAPPER.map(dto, Parking.class);
	}

}
