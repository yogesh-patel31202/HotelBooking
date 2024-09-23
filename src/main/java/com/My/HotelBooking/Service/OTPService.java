package com.My.HotelBooking.Service;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OTPService {

    private Map<String, String> otpStorage = new HashMap<>();

    // Generate a random OTP
    public String generateOTP(String mobileNumber) {
        String otp = String.valueOf(new Random().nextInt(999999));  // 6-digit OTP
        otpStorage.put(mobileNumber, otp);
        return otp;
    }

    // Send OTP (Here you can integrate any SMS service to send the OTP)
    public void sendOTP(String mobileNumber, String otp) {
        // Integrate with SMS service to send OTP

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        String key_2fa = "5fxxxx7a8-76be-11ef-8b17-02sdxxxd936042"; // this is not original key replace with your key
        Request request = new Request.Builder()
                .url("https://2factor.in/API/V1/"+key_2fa+"/SMS/+91"+mobileNumber+"/AUTOGEN2/OTP1")
                .method("GET", body)
                .build();

        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Sending OTP " + otp + " to mobile number: " + mobileNumber);
    }

    // Validate the OTP
    public boolean validateOTP(String mobileNumber, String otp) {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        String key_2fa = "5f3bb7a8-76be-11ef-8b17-0200cd936042";
        Request request = new Request.Builder()
                .url("https://2factor.in/API/V1/"+key_2fa+"/SMS/VERIFY3/"+mobileNumber+"/"+otp)
                .method("GET", body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(!response.isSuccessful()){
                return true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

}



