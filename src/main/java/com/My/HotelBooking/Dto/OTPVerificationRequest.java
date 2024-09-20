package com.My.HotelBooking.Dto;

import lombok.Data;

@Data
public class OTPVerificationRequest {
    private String mobileNumber;
    private String otp;
}
