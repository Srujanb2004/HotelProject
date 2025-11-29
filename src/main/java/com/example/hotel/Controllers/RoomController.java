package com.example.hotel.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.Dto.ApiResponse;
import com.example.hotel.Dto.CreateRoomDto;
import com.example.hotel.HotelException.HotelExceptions;
import com.example.hotel.Service.RoomService;
import com.example.hotel.entities.Room;

import lombok.RequiredArgsConstructor;

@RequestMapping("/rooms")
@RestController
@RequiredArgsConstructor
public class RoomController {

	private final RoomService roomservice;

	@PostMapping("/createRoom")
	public ResponseEntity<?> createRoom(@RequestBody CreateRoomDto roomsDto) {

		Room savedRoom;
		try {
			savedRoom = roomservice.createRoom(roomsDto.getUserId(), roomsDto.getRoom());
			ApiResponse response = roomservice.myResponse("success", "Room created successfully", savedRoom);
			return ResponseEntity.ok(response);

		} catch (HotelExceptions e) {
			ApiResponse apiResponse = roomservice.myResponse("failed", e.getMessage(), null);

			return ResponseEntity.badRequest().body(apiResponse);
		}


	}

}
