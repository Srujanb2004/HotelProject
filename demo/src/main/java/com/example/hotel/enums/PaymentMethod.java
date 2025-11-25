package com.example.hotel.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {

    CASH("Cash payment"),
    CREDIT_CARD("Credit card payment"),
    DEBIT_CARD("Debit card payment"),
    UPI("UPI payment"),
    NET_BANKING("Net banking payment"),
    WALLET("Digital wallet payment");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }
}
