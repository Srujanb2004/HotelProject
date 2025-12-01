package com.example.hotel.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.Dto.ApiResponse;
import com.example.hotel.Dto.BookingDto;
import com.example.hotel.Dto.CreateRoomDto;
import com.example.hotel.Dto.RoomsDto;
import com.example.hotel.Dto.UserUpdateDto;
import com.example.hotel.HotelException.HotelExceptions;
import com.example.hotel.Service.RoomService;
import com.example.hotel.entities.Booking;
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
	
	@PatchMapping("/updateRoom/{id}")
	public ResponseEntity<?> updateRoom(@PathVariable Integer id,@RequestBody RoomsDto roomsDto){

		Room updatedRoom;
		try {
			updatedRoom = roomservice.updateRoom(id,roomsDto);
			ApiResponse response = roomservice.myResponse("success", "Room Updated successfully", updatedRoom);
			return ResponseEntity.ok(response);

		} catch (HotelExceptions e) {
			ApiResponse apiResponse = roomservice.myResponse("failed", e.getMessage(), null);

			return ResponseEntity.badRequest().body(apiResponse);
		}


	}
	
	@DeleteMapping("/deleteRoom/{userId}/{roomNum}")
	public ResponseEntity<?> deleteRoom(@PathVariable Integer userId,@PathVariable Integer roomNum){

		try {
			String deltedRoom = roomservice.deleteRoom(userId,roomNum);
			ApiResponse response = roomservice.myResponse("success", "Room Deleted successfully", deltedRoom);
			return ResponseEntity.ok(response);

		} catch (HotelExceptions e) {
			ApiResponse apiResponse = roomservice.myResponse("failed", e.getMessage(), null);

			return ResponseEntity.badRequest().body(apiResponse);
		}


	}
	
	@PostMapping("/bookRoom")
	public ResponseEntity<?> bookRoom(@RequestBody BookingDto bookingDto) throws HotelExceptions {

		System.out.println("in book rooms");
		Booking booking;
		try {
			booking =roomservice.bookRoom(bookingDto);

			ApiResponse apiResponse = roomservice.myResponse("success", "Room Booked Successfully", booking);
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse apiResponse = roomservice.myResponse("failed",e.getMessage(), null);
			return ResponseEntity.badRequest().body(apiResponse);
		}

	}

}
