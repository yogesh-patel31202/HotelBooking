package com.My.HotelBooking.Repository;

import com.My.HotelBooking.Entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
