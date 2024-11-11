package com.example.Crop_Monitoring_system.Service;

import com.example.Crop_Monitoring_system.dto.impl.CropDTO;
import com.example.Crop_Monitoring_system.dto.impl.FieldDTO;

public interface CropService{
    void saveCrop(CropDTO cropDTO);
}
