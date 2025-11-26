package com.example.hotel.entities;

import com.example.hotel.enums.RoomStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "room_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_id", nullable = false)
    private Integer roomId;

    @NotNull
    @Column(name = "room_number", nullable = false)
    private Integer roomNum;

    @NotBlank
    @Column(name = "room_type", nullable = false)
    private String roomType;

    @NotNull
    @Column(name = "price_per_night", precision = 10, scale = 2, nullable = false)
    private BigDecimal pricePerNight;

    @NotBlank
    @Column(name = "room_desc", nullable = false)
    private String roomDesc;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "room_status", nullable = false)
    private RoomStatus roomStatus;
}
