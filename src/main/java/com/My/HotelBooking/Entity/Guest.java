package com.My.HotelBooking.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "guest")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phoneNumber;

    private String address;

    // Constructors, getters, and setters
}

