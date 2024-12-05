package com.example.Crop_Monitoring_system.dto.impl;

import com.example.Crop_Monitoring_system.dto.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO implements UserStatus {
    private String user_id;
    private String email;
    private String password;
    private String role;
}
