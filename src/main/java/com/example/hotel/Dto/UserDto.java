package com.example.hotel.Dto;


import com.example.hotel.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private String email;
    private Boolean active;
    private Role role;


	
    
     

}
