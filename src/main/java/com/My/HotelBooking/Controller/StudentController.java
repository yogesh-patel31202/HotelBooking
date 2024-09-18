package com.My.HotelBooking.Controller;

import com.My.HotelBooking.Entity.Student;
import com.My.HotelBooking.Entity.User;
import com.My.HotelBooking.Service.StudentService;
import com.My.HotelBooking.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // Create a new user
    @PostMapping
    public ResponseEntity<Student> createUser(@RequestBody Student user) {
        Student createdUser = studentService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<Student>> getAllUsers() {
        List<Student> users = studentService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
