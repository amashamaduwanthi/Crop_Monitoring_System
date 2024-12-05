package com.example.Crop_Monitoring_system.Service;


import com.example.Crop_Monitoring_system.Secure.JWTAuthResponse;
import com.example.Crop_Monitoring_system.Secure.SignIn;
import com.example.Crop_Monitoring_system.Secure.SignUp;
import com.example.Crop_Monitoring_system.dto.impl.PasswordDto;

public interface AuthenticationService {
    JWTAuthResponse signUp(SignUp signUp);
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse refreshToken(String refreshToken);
    void changePassword(PasswordDto passwordDto);
}
