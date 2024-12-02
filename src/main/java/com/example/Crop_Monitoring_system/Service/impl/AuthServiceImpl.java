package com.example.Crop_Monitoring_system.Service.impl;

import com.example.Crop_Monitoring_system.Secure.JWTAuthResponse;
import com.example.Crop_Monitoring_system.Secure.SignIn;
import com.example.Crop_Monitoring_system.Service.AuthService;
import com.example.Crop_Monitoring_system.dto.impl.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        return null;
    }

    @Override
    public JWTAuthResponse signUp(UserDTO userDTO) {
        return null;
    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        return null;
    }
}
