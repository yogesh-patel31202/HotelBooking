package com.My.HotelBooking.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Entity
@Data
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;

    @Column(nullable = false)
    private String hotelName;

    @Column(nullable = false)
    private String hotelAddress;

    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String phoneNumber;
    private String email;
    private String website;

    @Column(precision = 2)
    private Double starRating;

    private Integer totalRooms;

    @Column(length = 1000)
    private String amenities;

    private Double pricePerNight;

    // One hotel can have many rooms
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<Room> rooms;

    // Constructors, getters, and setters
}
