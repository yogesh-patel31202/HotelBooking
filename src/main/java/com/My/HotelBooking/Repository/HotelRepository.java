package com.My.HotelBooking.Repository;

import com.My.HotelBooking.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    // Custom query using @Query annotation
    @Query("SELECT h FROM Hotel h WHERE h.city = :city")
    List<Hotel> findHotelsByCity(@Param("city") String city);

/*   In case of use native queries
    @Query(value = "SELECT * FROM hotel WHERE city = :city", nativeQuery = true)
    List<Hotel> findHotelsByCityNative(@Param("city") String city);
    */


}

