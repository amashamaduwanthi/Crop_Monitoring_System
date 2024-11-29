package com.example.Crop_Monitoring_system.Service;

import com.example.Crop_Monitoring_system.dto.StaffStatus;
import com.example.Crop_Monitoring_system.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);

    List<StaffDTO> getAllStaff();

    StaffStatus getStaff(String id);

    void deleteStaff(String id);

    void updateStaff(String id,StaffDTO staffDTO);

//    StaffDTO getStaffByName(String id);
}
