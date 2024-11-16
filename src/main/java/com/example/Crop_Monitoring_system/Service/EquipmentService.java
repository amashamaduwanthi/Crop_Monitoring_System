package com.example.Crop_Monitoring_system.Service;

import com.example.Crop_Monitoring_system.dto.EquipmentStatus;
import com.example.Crop_Monitoring_system.dto.impl.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);

    List<EquipmentDTO> getAllEquipment();

    EquipmentStatus getEquipment(String equipmentId);

    void deleteEquipment(String equipmentId);

    void updateEquipment(String equipmentId,EquipmentDTO equipmentDTO);
}
