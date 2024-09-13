package com.My.HotelBooking.Repository;

import com.My.HotelBooking.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
