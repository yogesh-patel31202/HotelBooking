package com.My.HotelBooking.Service;

import com.My.HotelBooking.Entity.Role;
import com.My.HotelBooking.Entity.Student;
import com.My.HotelBooking.Entity.User;
import com.My.HotelBooking.Repository.RoleRepository;
import com.My.HotelBooking.Repository.StudentRepository;
import com.My.HotelBooking.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Create or Save a User with Roles
    public Student createUser(Student user) {

        return studentRepository.save(user); // Save the user with roles
    }

    // Get all users
    public List<Student> getAllUsers() {
        return studentRepository.findAll();
    }

    // Get user by ID
    public Optional<Student> getUserById(Long id) {
        return studentRepository.findById(id);
    }

    // Update user

    // Delete user by ID
    public void deleteUserById(Long id) {
        studentRepository.deleteById(id);
    }
}
