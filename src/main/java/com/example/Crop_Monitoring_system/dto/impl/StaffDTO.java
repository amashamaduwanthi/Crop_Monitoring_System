package com.example.Crop_Monitoring_system.dto.impl;

import com.example.Crop_Monitoring_system.dto.StaffStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements StaffStatus {
    private String id;
    private String first_name;
    private String last_name;
    private String designation;
    private String gender;
    private String joined_date;
    private String dob;
    private String address;
    private String contact_no;
    private String email;
    private String role;
    private List<FieldDTO> fields;

}
