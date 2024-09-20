package com.My.HotelBooking.Controller;

import com.My.HotelBooking.Dto.LoginRequest;
import com.My.HotelBooking.Dto.OTPVerificationRequest;
import com.My.HotelBooking.Dto.SignupRequest;
import com.My.HotelBooking.Entity.User;
import com.My.HotelBooking.Service.OTPService;
import com.My.HotelBooking.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private OTPService otpService;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) {
        // Check if user already exists by mobile number
        if (userService.existsByMobileNumber(signupRequest.getMobileNumber())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }

        // Create a new user with verification_status PENDING
        User newUser = new User();
        newUser.setMobileNumber(signupRequest.getMobileNumber());
        newUser.setName(signupRequest.getName());
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPassword(signupRequest.getPassword());
        newUser.setVerificationStatus("PENDING");
        userService.saveUser(newUser);

        // Generate and send OTP
        String otp = otpService.generateOTP(newUser.getMobileNumber());
        otpService.sendOTP(newUser.getMobileNumber(), otp);

        return ResponseEntity.ok("Signup successful. OTP sent for verification.");
    }

    // Verify OTP method
    @PostMapping("/verify-otp-signup")
    public ResponseEntity<?> verifyOtpSignUp(@RequestBody OTPVerificationRequest otpRequest) {
        User user = userService.findByMobileNumber(otpRequest.getMobileNumber());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        // Validate OTP
        boolean isValid = otpService.validateOTP(otpRequest.getMobileNumber(), otpRequest.getOtp());

        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
        }

        // Update verification_status to SUCCESS
        user.setVerificationStatus("SUCCESS");
        userService.saveUser(user);

        return ResponseEntity.ok("User verified successfully.");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Check if user exists by mobile number
        User user = userService.findByMobileNumber(loginRequest.getMobileNumber());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        // Generate and send OTP
        String otp = otpService.generateOTP(loginRequest.getMobileNumber());
        otpService.sendOTP(loginRequest.getMobileNumber(), otp);

        return ResponseEntity.ok("OTP sent successfully");
    }

    @PostMapping("/verify-otp-login")
    public ResponseEntity<?> verifyOtLogin(@RequestBody OTPVerificationRequest otpRequest) {
        boolean isValid = otpService.validateOTP(otpRequest.getMobileNumber(), otpRequest.getOtp());

        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
        }

        // Login success, return a token or login success message
        return ResponseEntity.ok("Login successful");
    }
}
