package com.example.hotel.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.hotel.Dto.ApiResponse;
import com.example.hotel.Dto.RoomsDto;
import com.example.hotel.HotelException.HotelExceptions;
import com.example.hotel.entities.Room;
import com.example.hotel.entities.User;
import com.example.hotel.enums.Role;
import com.example.hotel.repository.RoomRepository;
import com.example.hotel.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {

	private final RoomRepository roomRepository;
	private final UserRepository userRepository;

	public ApiResponse myResponse(String status, String message, Object data) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(data);
		apiResponse.setMessage(message);
		apiResponse.setStatus(status);
		apiResponse.setTimestamp(LocalDateTime.now());

		return apiResponse;
	}

	public Room createRoom(Integer userId, RoomsDto room) throws HotelExceptions {

		User admin = userRepository.findById(userId).orElseThrow(() -> new HotelExceptions("Admin not found"));

		if (admin.getRole() != Role.ADMIN) {
			throw new HotelExceptions("Only ADMIN users can create rooms");
		}
		Room createRoom = new Room();
		createRoom.setPricePerNight(room.getPricePerNight());
		createRoom.setRoomDesc(room.getRoomDesc());
		createRoom.setRoomType(room.getRoomType());
		createRoom.setRoomStatus(room.getRoomStatus());
		createRoom.setRoomNum(room.getRoomNum());

		return roomRepository.save(createRoom);

	}

}
