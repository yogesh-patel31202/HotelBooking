package com.My.HotelBooking.Repository;

import com.My.HotelBooking.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
