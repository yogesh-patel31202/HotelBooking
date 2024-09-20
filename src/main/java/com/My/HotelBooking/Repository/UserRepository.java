package com.My.HotelBooking.Repository;

import com.My.HotelBooking.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByMobileNumber(String mobileNumber);
    boolean existsByMobileNumber(String mobileNumber);
}
