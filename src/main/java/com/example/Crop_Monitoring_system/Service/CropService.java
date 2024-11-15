package com.example.Crop_Monitoring_system.Service;

import com.example.Crop_Monitoring_system.dto.CropStatus;
import com.example.Crop_Monitoring_system.dto.impl.CropDTO;
import com.example.Crop_Monitoring_system.dto.impl.FieldDTO;

import java.util.List;

public interface CropService{
    void saveCrop(CropDTO cropDTO);
    List<CropDTO> getAllCrops();

    CropStatus getCrop(String cropCode);

    void deleteCrop(String cropCode);

    void updateCrop(String cropCode,CropDTO cropDTO);
}
