package com.example.hotel.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.hotel.Dto.ApiResponse;
import com.example.hotel.Dto.UserDto;
import com.example.hotel.Dto.UserUpdateDto;
import com.example.hotel.HotelException.HotelExceptions;
import com.example.hotel.entities.User;
import com.example.hotel.enums.Role;
import com.example.hotel.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	
	public ApiResponse myResponse(String status,String message,Object data)
	{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setData(data);
		apiResponse.setMessage(message);
		apiResponse.setStatus(status);
		apiResponse.setTimestamp(LocalDateTime.now());

		return  apiResponse;
	}

	public User registerUser(UserDto dto) throws HotelExceptions {

		if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
			throw new HotelExceptions("Email already registered");
		}
		
		if (dto.getRole() == null) {
	        throw new HotelExceptions("Role is required and can be: ADMIN, CUSTOMER, RECEPTIONIST, HOUSEKEEPING");
	    }

	    if (!dto.getRole().name().equals(dto.getRole().name().toUpperCase())) {
	        throw new HotelExceptions("Role must be CAPITAL letters. Allowed values: ADMIN, CUSTOMER, RECEPTIONIST, HOUSEKEEPING");
	    }
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setEmail(dto.getEmail());
		user.setActive(dto.getActive());
		user.setRoom(null);
		user.setRole(dto.getRole());

		return userRepository.save(user);

	}

	public User updateUserDetail(Integer id, UserUpdateDto dto) throws HotelExceptions {
		User user = userRepository.findById(id).orElseThrow(() -> new HotelExceptions("User not found"));

		if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
			throw new HotelExceptions("Email already registered");
		}

		if (dto.getEmail() != null)
			user.setEmail(dto.getEmail());

		if (dto.getActive() != null)
			user.setActive(dto.getActive());

		if (dto.getUsername() != null)
			user.setUsername(dto.getUsername());

		if (dto.getPassword() != null)
			user.setPassword(dto.getPassword());

		return userRepository.save(user);
	}

	public String deleteUser(Integer id) throws HotelExceptions {
		User user = userRepository.findById(id).orElseThrow(() -> new HotelExceptions("User not found"));
		String deletedUsername=user.getUsername();
		userRepository.delete(user);

		return "User with id -> "+id+" -> "+deletedUsername+ " has been deleted successfully";

	}

}
