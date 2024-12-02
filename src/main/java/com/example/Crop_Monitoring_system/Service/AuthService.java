package com.example.Crop_Monitoring_system.Service;

import com.example.Crop_Monitoring_system.Secure.JWTAuthResponse;
import com.example.Crop_Monitoring_system.Secure.SignIn;
import com.example.Crop_Monitoring_system.dto.impl.UserDTO;

public interface AuthService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(UserDTO userDTO);
    JWTAuthResponse refreshToken(String accessToken);
}
