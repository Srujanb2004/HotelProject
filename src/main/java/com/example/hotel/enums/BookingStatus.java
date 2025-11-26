package com.example.hotel.enums;

import lombok.Getter;

@Getter
public enum BookingStatus {

    BOOKED("Room has been booked"),
    CHECKED_IN("Guest checked in"),
    CHECKED_OUT("Guest checked out"),
    CANCELLED("Booking cancelled");

    private final String description;

    BookingStatus(String description) {
        this.description = description;
    }
}
