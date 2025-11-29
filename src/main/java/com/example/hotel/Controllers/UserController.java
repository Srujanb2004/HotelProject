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
import com.example.hotel.Dto.UserDto;
import com.example.hotel.Dto.UserUpdateDto;
import com.example.hotel.HotelException.HotelExceptions;
import com.example.hotel.Service.UserService;
import com.example.hotel.entities.User;

import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserDto userdto) throws HotelExceptions {
		
		try {
			User registeredUser = userService.registerUser(userdto);
			ApiResponse apiResponse = userService.myResponse("success", "User registered successfully", registeredUser);
			return ResponseEntity.ok(apiResponse);

		} catch (HotelExceptions e) {
			ApiResponse apiResponse = userService.myResponse("failed", e.getMessage(), null);

			return ResponseEntity.badRequest().body(apiResponse);
		}
		
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserUpdateDto dto)
			throws HotelExceptions {

		try {
			User updatedUser = userService.updateUserDetail(id, dto);
			ApiResponse apiResponse = userService.myResponse("success", "User Updated successfully", updatedUser);
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse apiResponse = userService.myResponse("failed", e.getMessage(), null);
			return ResponseEntity.badRequest().body(apiResponse);
		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id) throws HotelExceptions {

		try {
			String msg = userService.deleteUser(id);

			ApiResponse apiResponse = userService.myResponse("success", msg, null);
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse apiResponse = userService.myResponse("failed", e.getMessage(), null);
			return ResponseEntity.badRequest().body(apiResponse);
		}

	}

}
