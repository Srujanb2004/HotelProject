package com.example.hotel.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {

	private String username;
	private String email;
	private String password;
	private Boolean active;

}
