package com.example.hotel.Dto;

import java.math.BigDecimal;

import com.example.hotel.enums.RoomStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomsDto {

	private Integer roomId;
	
	private Integer roomNum;

	private String roomType;

	private BigDecimal pricePerNight;

	private String roomDesc;

	private RoomStatus roomStatus;

}
