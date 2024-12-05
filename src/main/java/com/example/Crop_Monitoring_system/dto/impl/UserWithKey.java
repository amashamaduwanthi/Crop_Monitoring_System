package com.example.Crop_Monitoring_system.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserWithKey {
    private String email;
    private String code;
}
