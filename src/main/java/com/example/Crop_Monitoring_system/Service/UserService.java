package com.example.Crop_Monitoring_system.Service;


import com.example.Crop_Monitoring_system.dto.impl.UserWithKey;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
    boolean sendCodeToChangePassword(UserWithKey userWithKey);
}
