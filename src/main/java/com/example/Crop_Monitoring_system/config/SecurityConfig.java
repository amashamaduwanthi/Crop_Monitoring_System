package com.example.Crop_Monitoring_system.config;

import com.example.Crop_Monitoring_system.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserService userService;
    private final JwtConfigFilter jwtConfigFilter;

}
