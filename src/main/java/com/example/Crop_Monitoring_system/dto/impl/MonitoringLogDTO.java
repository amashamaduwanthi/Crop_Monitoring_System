package com.example.Crop_Monitoring_system.dto.impl;

import com.example.Crop_Monitoring_system.dto.MonitoringLogStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDTO implements MonitoringLogStatus {
    private String log_code;
    private String log_date;
    private String log_details;
    private String observed_image;
    private List<FieldDTO> fields;
    private List<CropDTO> crops;
    private List<StaffDTO> staff;
}
