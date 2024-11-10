package com.example.Crop_Monitoring_system.customerStatusCode;

import com.example.Crop_Monitoring_system.dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements UserStatus, StaffStatus, VehicleStatus, CropStatus, FieldStatus,EquipmentStatus,MonitoringLogStatus {
    private int statusCode;
    private String statusMessage;
}
