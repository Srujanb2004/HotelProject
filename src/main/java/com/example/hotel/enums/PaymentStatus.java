package com.example.hotel.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {

    PENDING("Payment initiated but not completed"),
    COMPLETED("Payment completed successfully"),
    FAILED("Payment failed"),
    REFUNDED("Payment refunded"),
    CANCELLED("Payment cancelled");

    private final String description;

    PaymentStatus(String description) {
        this.description = description;
    }
}
