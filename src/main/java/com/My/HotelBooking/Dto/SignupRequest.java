package com.My.HotelBooking.Dto;

import lombok.Data;

@Data
public class SignupRequest {

    private String name;
    private String email;
    private String mobileNumber;
    private String password;


}
