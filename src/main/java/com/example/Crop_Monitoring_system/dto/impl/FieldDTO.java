package com.example.Crop_Monitoring_system.dto.impl;

import com.example.Crop_Monitoring_system.dto.FieldStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements FieldStatus {
    private String field_code;
    private String field_name;
    private String location;
    private Double extent_size;
    private String field_image1;
    private String field_image2;
    private List<CropDTO> crops;
    private List<StaffDTO> allocated_staff;
}
