package com.example.Crop_Monitoring_system.Service;

import com.example.Crop_Monitoring_system.dto.FieldStatus;
import com.example.Crop_Monitoring_system.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);
    List<FieldDTO> getAllFields();

    FieldStatus getField(String fieldCode);

    void deleteField(String fieldCode);

    void updateField(String fieldCode,FieldDTO fieldDTO);
}
