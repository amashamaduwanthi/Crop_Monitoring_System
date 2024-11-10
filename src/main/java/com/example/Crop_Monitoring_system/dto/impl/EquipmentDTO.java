package com.example.Crop_Monitoring_system.dto.impl;

import com.example.Crop_Monitoring_system.dto.EquipmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements EquipmentStatus {
    private String equipment_id;
    private String name;
    private String type;
    private String status;
    private StaffDTO assigned_staff;
    private FieldDTO assigned_field;
}
