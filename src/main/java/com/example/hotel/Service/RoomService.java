package com.example.hotel.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.hotel.Dto.ApiResponse;
import com.example.hotel.Dto.BookingDto;
import com.example.hotel.Dto.CreateRoomDto;
import com.example.hotel.Dto.RoomsDto;
import com.example.hotel.HotelException.HotelExceptions;
import com.example.hotel.entities.Booking;
import com.example.hotel.entities.Customer;
import com.example.hotel.entities.Room;
import com.example.hotel.entities.User;
import com.example.hotel.enums.BookingStatus;
import com.example.hotel.enums.Role;
import com.example.hotel.enums.RoomStatus;
import com.example.hotel.repository.BookingRepository;
import com.example.hotel.repository.CustomerRepository;
import com.example.hotel.repository.RoomRepository;
import com.example.hotel.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {

	private final RoomRepository roomRepository;
	private final UserRepository userRepository;
	private final CustomerRepository customerRepository;
	private final BookingRepository bookingRepository;

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

	public Room updateRoom(Integer id, RoomsDto roomsDto) throws HotelExceptions {
		User admin = userRepository.findById(id).orElseThrow(() -> new HotelExceptions("Admin not found"));

		Room updateRoom = roomRepository.findById(roomsDto.getRoomId())
				.orElseThrow(() -> new HotelExceptions("Room not found"));

		if (admin.getRole() != Role.ADMIN) {
			throw new HotelExceptions("Only ADMIN users can create rooms");
		}

		if (roomsDto.getPricePerNight() != null) {
			updateRoom.setPricePerNight(roomsDto.getPricePerNight());

		}
		if (roomsDto.getRoomDesc() != null) {
			updateRoom.setRoomDesc(roomsDto.getRoomDesc());

		}
		if (roomsDto.getRoomType() != null) {
			updateRoom.setRoomType(roomsDto.getRoomType());

		}
		if (roomsDto.getRoomStatus() != null) {
			updateRoom.setRoomStatus(roomsDto.getRoomStatus());

		}
		return roomRepository.save(updateRoom);

	}

	public String deleteRoom(Integer userId, Integer roomNum) throws HotelExceptions {
		User admin = userRepository.findById(userId).orElseThrow(() -> new HotelExceptions("Admin not found"));

		Room deleteRoom = roomRepository.findByRoomNum(roomNum)
				.orElseThrow(() -> new HotelExceptions("Room not found"));
		
//		Integer roomNumber=deleteRoom.getRoomNum();
		
		 roomRepository.delete(deleteRoom);
		 return "Room with roomNumber -> "+roomNum+" -> "+" has been deleted successfully";
	}

	
	public Booking bookRoom(BookingDto bookingDto) throws HotelExceptions {
		Room findRoom = roomRepository.findByRoomNum(bookingDto.getRoomNum())
				.orElseThrow(() -> new HotelExceptions("Room not found"));
		
		if(findRoom.getRoomStatus()==RoomStatus.CONFIRMED)
		{
			throw new HotelExceptions("Room is not free now ");
		}
		
		findRoom.setRoomStatus(RoomStatus.CONFIRMED);
		
		Customer customer=new Customer();
		customer.setEmail(bookingDto.getCustomer().getEmail());
		customer.setAddress(bookingDto.getCustomer().getAddress());
		customer.setFullName(bookingDto.getCustomer().getFullName());
		customer.setIdProofNumber(bookingDto.getCustomer().getIdProofNumber());
		customer.setIdProofType(bookingDto.getCustomer().getIdProofType());
		customer.setPhone(bookingDto.getCustomer().getPhone());
		
		customerRepository.save(customer);
		
		
		
		Booking booking=new Booking();
		booking.setCustomer(customer);
		booking.setCheckInDate(bookingDto.getCheckInDate());
		booking.setCheckOutDate(bookingDto.getCheckOutDate());
		booking.setRoom(findRoom);
		booking.setStatus(BookingStatus.BOOKED);
		booking.setTotalAmount(bookingDto.getTotalAmount());

		
		
		
		return bookingRepository.save(booking);
	}

}
