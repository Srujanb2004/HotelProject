package com.example.hotel.enums;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("Administrator with full system access"),
    RECEPTIONIST("Front desk staff managing bookings and check-ins"),
    CUSTOMER("Registered hotel customer"),
    HOUSEKEEPING("Responsible for room cleaning and maintenance");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
