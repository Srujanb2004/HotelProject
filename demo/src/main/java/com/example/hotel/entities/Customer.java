package com.example.hotel.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @NotBlank
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotBlank
    @Column(name = "id_proof_type", nullable = false)
    private String idProofType;

    @NotBlank
    @Column(name = "id_proof_number", nullable = false)
    private String idProofNumber;

    @NotBlank
    @Column(name = "address", nullable = false)
    private String address;
}
