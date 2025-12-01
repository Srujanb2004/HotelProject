package com.example.hotel.Dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.hotel.enums.BookingStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDto {

	private Integer roomNum;

	private CustomerDto customer;

	private LocalDateTime checkInDate;
	private LocalDateTime checkOutDate;

	private BigDecimal totalAmount;

	private BookingStatus status;

}
