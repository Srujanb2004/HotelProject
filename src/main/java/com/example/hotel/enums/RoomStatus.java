package com.example.hotel.enums;

import lombok.Getter;

@Getter
public enum RoomStatus {

	FREE("Is Available for Booking"),
    PENDING("Pending confirmation"),
    CONFIRMED("Room confirmed"),
    CHECKED_IN("Guest checked in"),
    CHECKED_OUT("Guest checked out"),
    CANCELLED("Booking cancelled"),
    REFUNDED("Payment refunded"),
    NO_SHOW("Guest did not arrive");

    private final String description;

    RoomStatus(String description) {
        this.description = description;
    }
}
