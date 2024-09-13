package com.My.HotelBooking.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Column(nullable = false)
    private String roomNumber;

    private String roomType;
    private String bedType;

    @Column(nullable = false)
    private Boolean isAvailable;

    private Double pricePerNight;

    // Constructors, getters, and setters
}
