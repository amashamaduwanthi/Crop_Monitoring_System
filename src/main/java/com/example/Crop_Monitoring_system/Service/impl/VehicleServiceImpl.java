package com.example.Crop_Monitoring_system.Service.impl;

import com.example.Crop_Monitoring_system.Exception.DataPersistException;
import com.example.Crop_Monitoring_system.Exception.VehicleNotFoundException;
import com.example.Crop_Monitoring_system.Service.VehicleService;
import com.example.Crop_Monitoring_system.customerStatusCode.SelectedErrorStatus;
import com.example.Crop_Monitoring_system.dao.VehicleDao;
import com.example.Crop_Monitoring_system.dto.VehicleStatus;
import com.example.Crop_Monitoring_system.dto.impl.VehicleDTO;
import com.example.Crop_Monitoring_system.entity.impl.StaffEntity;
import com.example.Crop_Monitoring_system.entity.impl.VehicleEntity;
import com.example.Crop_Monitoring_system.util.AppUtil;
import com.example.Crop_Monitoring_system.util.Mapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        vehicleDTO.setVehicle_code(AppUtil.generateVehicleId());
        VehicleEntity saveVehicle = vehicleDao.save(mapping.toVehicleEntity(vehicleDTO));
        if(saveVehicle == null) {
            throw new DataPersistException("Vehicle not saved");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return mapping.toVehicleDTOList(vehicleDao.findAll());
    }

    @Override
    public VehicleStatus getVehicle(String vehicleCode) {
        if(vehicleDao.existsById(vehicleCode)) {
            var selectedVehicle = vehicleDao.getReferenceById(vehicleCode);
            return mapping.toVehicleDTO(selectedVehicle);
        }else {
            return new SelectedErrorStatus(2,"Selected Vehicle Not Found");
        }
    }

    @Override
    public void deleteVehicle(String vehicleCode) {
        Optional<VehicleEntity> foundVehicle = vehicleDao.findById(vehicleCode);
        if(foundVehicle.isPresent()) {
            throw new VehicleNotFoundException("Vehicle Not Found");
        }else{
            vehicleDao.deleteById(vehicleCode);
        }
    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) {
        Optional<VehicleEntity> tmpVehicle = vehicleDao.findById(vehicleCode);
        if(!tmpVehicle.isPresent()) {
            throw new VehicleNotFoundException("Vehicle Not Found");
        }else {
            tmpVehicle.get().setLicensePlateNumber(vehicleDTO.getLicensePlateNumber());
            tmpVehicle.get().setVehicle_code(vehicleDTO.getVehicle_code());
            tmpVehicle.get().setFuelType(vehicleDTO.getFuelType());
            tmpVehicle.get().setStatus(vehicleDTO.getStatus());
            tmpVehicle.get().setRemarks(vehicleDTO.getRemarks());
            StaffEntity staffEntity = mapping.toStaffEntity(vehicleDTO.getAssigned_staff());
            tmpVehicle.get().setAssigned_staff(staffEntity);
        }
    }
}


