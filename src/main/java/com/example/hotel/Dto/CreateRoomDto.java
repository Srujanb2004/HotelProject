package com.example.hotel.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomDto {

	private Integer userId;     
    private RoomsDto room;
}
