package com.example.hotel.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

	private String fullName;
    private String email;
    private String phone;
    private String idProofType;
    private String idProofNumber;
    private String address;
}
