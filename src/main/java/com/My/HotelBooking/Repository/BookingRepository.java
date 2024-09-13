package com.My.HotelBooking.Repository;

import com.My.HotelBooking.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
