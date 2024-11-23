package com.example.Crop_Monitoring_system.Service.impl;

import com.example.Crop_Monitoring_system.Exception.StaffNotFoundException;
import com.example.Crop_Monitoring_system.Service.StaffService;
import com.example.Crop_Monitoring_system.customerStatusCode.SelectedErrorStatus;
import com.example.Crop_Monitoring_system.dao.StaffDao;
import com.example.Crop_Monitoring_system.dto.StaffStatus;
import com.example.Crop_Monitoring_system.dto.impl.StaffDTO;
import com.example.Crop_Monitoring_system.entity.impl.FieldEntity;
import com.example.Crop_Monitoring_system.entity.impl.StaffEntity;
import com.example.Crop_Monitoring_system.entity.impl.VehicleEntity;
import com.example.Crop_Monitoring_system.util.AppUtil;
import com.example.Crop_Monitoring_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveStaff(StaffDTO staffDTO) {

        StaffEntity staffEntity = staffDao.save(mapping.toStaffEntity(staffDTO));
        if(staffEntity == null) {
            throw new StaffNotFoundException("Staff Member Not Found");
        }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return mapping.toStaffDTOList(staffDao.findAll());
    }

    @Override
    public StaffStatus getStaff(String id) {
        if(staffDao.existsById(id)){
            var selectedStaff = staffDao.getReferenceById(id);
            return mapping.toStaffDTO(selectedStaff);
        }else {
            return new SelectedErrorStatus(2,"Selected Staff Member Not Found");
        }
    }

    @Override
    public void deleteStaff(String id) {
        Optional<StaffEntity> foundStaff = staffDao.findById(id);
        if(!foundStaff.isPresent()) {
            throw new StaffNotFoundException("Staff Member Not Found");
        }else {
            staffDao.deleteById(id);
        }
    }

    @Override
    public void updateStaff(String id, StaffDTO staffDTO) {
        Optional<StaffEntity> tmpStaff = staffDao.findById(id);
        if(!tmpStaff.isPresent()) {
            throw new StaffNotFoundException("Staff Member Not Found");
        }else{
            tmpStaff.get().setFirst_name(staffDTO.getFirst_name());
            tmpStaff.get().setLast_name(staffDTO.getLast_name());
            tmpStaff.get().setDesignation(staffDTO.getDesignation());
            tmpStaff.get().setGender(staffDTO.getGender());
            tmpStaff.get().setJoined_date(staffDTO.getJoined_date());
            tmpStaff.get().setDob(staffDTO.getDob());
            tmpStaff.get().setAddress(staffDTO.getAddress());
            tmpStaff.get().setContact_no(staffDTO.getContact_no());
            tmpStaff.get().setEmail(staffDTO.getEmail());
            tmpStaff.get().setRole(staffDTO.getRole());
//            List<FieldEntity> fieldEntityList = mapping.toFieldEntityList(staffDTO.getFields());
//            tmpStaff.get().setFields(fieldEntityList);
//            List<VehicleEntity> vehicleEntityList = mapping.toVehicleEntityList(staffDTO.getVehicles());
//            tmpStaff.get().setVehicles(vehicleEntityList);
        }
    }

}
