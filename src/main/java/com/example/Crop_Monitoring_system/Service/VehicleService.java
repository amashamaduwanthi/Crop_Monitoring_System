package com.example.Crop_Monitoring_system.Service;

import com.example.Crop_Monitoring_system.dto.VehicleStatus;
import com.example.Crop_Monitoring_system.dto.impl.VehicleDTO;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);

    List<VehicleDTO> getAllVehicles();

    VehicleStatus getVehicle(String vehicleCode);

    void deleteVehicle(String vehicleCode);

    void updateVehicle(String vehicleCode,VehicleDTO vehicleDTO);
}
