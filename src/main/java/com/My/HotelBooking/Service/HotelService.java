package com.My.HotelBooking.Service;

import com.My.HotelBooking.Entity.Hotel;
import com.My.HotelBooking.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class HotelService {

    private final Path root = Paths.get("uploads");

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getHotelsByCity(String city) {
        return hotelRepository.findHotelsByCity(city);
    }

}
