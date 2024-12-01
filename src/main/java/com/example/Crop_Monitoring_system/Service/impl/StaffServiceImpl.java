package com.example.Crop_Monitoring_system.Service.impl;

import com.example.Crop_Monitoring_system.Exception.FieldNotFoundException;
import com.example.Crop_Monitoring_system.Exception.StaffNotFoundException;
import com.example.Crop_Monitoring_system.Service.StaffService;
import com.example.Crop_Monitoring_system.customerStatusCode.SelectedErrorStatus;
import com.example.Crop_Monitoring_system.dao.FieldDao;
import com.example.Crop_Monitoring_system.dao.StaffDao;
import com.example.Crop_Monitoring_system.dto.StaffStatus;
import com.example.Crop_Monitoring_system.dto.impl.FieldDTO;
import com.example.Crop_Monitoring_system.dto.impl.StaffDTO;
import com.example.Crop_Monitoring_system.entity.impl.CropEntity;
import com.example.Crop_Monitoring_system.entity.impl.FieldEntity;
import com.example.Crop_Monitoring_system.entity.impl.StaffEntity;
import com.example.Crop_Monitoring_system.entity.impl.VehicleEntity;
import com.example.Crop_Monitoring_system.util.AppUtil;
import com.example.Crop_Monitoring_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;

    @Autowired
    private FieldDao fieldDao;
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
        List<StaffEntity> staffs = staffDao.findAll();
        return staffs.stream()
                .map(staff -> {
                    StaffDTO staffDTO = new StaffDTO();
                    staffDTO.setId(staff.getId());
                    staffDTO.setFirst_name(staff.getFirst_name());
                    staffDTO.setLast_name(staff.getLast_name());
                    staffDTO.setDesignation(staff.getDesignation());
                    staffDTO.setGender(staff.getGender());
                    staffDTO.setJoined_date(staff.getJoined_date());
                    staffDTO.setDob(staff.getDob());
                    staffDTO.setAddress(staff.getAddress());
                    staffDTO.setContact_no(staff.getContact_no());
                    staffDTO.setEmail(staff.getEmail());
                    staffDTO.setRole(staff.getRole());
                    List<FieldDTO> assignedFieldDTO = new ArrayList<>();
                    for (FieldEntity field : staff.getFields()) {
                        Optional<FieldEntity> fieldOpt = fieldDao.findById(field.getField_name());
                        if (fieldOpt.isPresent()) {
                            assignedFieldDTO.add(mapping.toFieldDTO(fieldOpt.get()));
                        }
                    }
                    staffDTO.setFields(assignedFieldDTO);
                    return staffDTO;
                })
                .collect(Collectors.toList());
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

    @Override
    public List<String> getAllStaffNames() {
        List<StaffEntity> staffEntities = staffDao.findAll();
        return staffEntities.stream()
                .map(StaffEntity::getFirst_name)
                .collect(Collectors.toList());
    }

    @Override
    public List<StaffDTO> getStaffListByName(List<String> staffs) {
        if(staffs.isEmpty() || staffs == null){
            return Collections.emptyList();
        }

        List<StaffEntity> staffEntities = staffDao.findByStaffNameList(staffs);

        if(staffEntities.isEmpty()){
            throw new StaffNotFoundException("Staff Member Not Found");
        }

        return staffEntities.stream()
                .map(mapping::toStaffDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StaffDTO getStaffByName(String assignedStaff) {
        Optional<StaffEntity> tmpStaff = staffDao.findByStaffName(assignedStaff);
        if(!tmpStaff.isPresent()){
            throw new StaffNotFoundException("Staff Member Not Found");
        }
        return mapping.toStaffDTO(tmpStaff.get());
    }

//    @Override
//    public StaffDTO getStaffByName(String id) {
//        Optional<StaffEntity> tmpField = staffDao.findByStaffName(id);
//        if(!tmpField.isPresent()){
//            throw new StaffNotFoundException("Staff not found: " + id);
//        }
//        return mapping.toStaffDTO(tmpField.get());
//    }

}


