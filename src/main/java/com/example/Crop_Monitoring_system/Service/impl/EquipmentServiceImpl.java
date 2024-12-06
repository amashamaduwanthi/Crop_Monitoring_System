package com.example.Crop_Monitoring_system.Service.impl;

import com.example.Crop_Monitoring_system.Exception.DataPersistException;
import com.example.Crop_Monitoring_system.Exception.EquipmentNotFoundException;
import com.example.Crop_Monitoring_system.Service.EquipmentService;
import com.example.Crop_Monitoring_system.customerStatusCode.SelectedErrorStatus;
import com.example.Crop_Monitoring_system.dao.EquipmentDao;
import com.example.Crop_Monitoring_system.dto.EquipmentStatus;
import com.example.Crop_Monitoring_system.dto.impl.EquipmentDTO;
import com.example.Crop_Monitoring_system.entity.impl.EquipmentEntity;
import com.example.Crop_Monitoring_system.entity.impl.FieldEntity;
import com.example.Crop_Monitoring_system.entity.impl.StaffEntity;
import com.example.Crop_Monitoring_system.util.AppUtil;
import com.example.Crop_Monitoring_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
//        equipmentDTO.setEquipment_id(AppUtil.generateEquipmentId());
        EquipmentEntity saveEquipment = equipmentDao.save(mapping.toEquipmentEntity(equipmentDTO));
        if(saveEquipment == null) {
            throw new DataPersistException("Equipment not saved");
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        return mapping.toEquipmentDTOList(equipmentDao.findAll());
    }

    @Override
    public EquipmentStatus getEquipment(String equipmentId) {
        if(equipmentDao.existsById(equipmentId)) {
            var selectedEquipment = equipmentDao.getReferenceById(equipmentId);
            return mapping.toEquipmentDTO(selectedEquipment);
        }else{
            return new SelectedErrorStatus(2,"Selected Equipment not found");
        }
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<EquipmentEntity> foundEquipment = equipmentDao.findById(equipmentId);
        if(!foundEquipment.isPresent()) {
            throw new EquipmentNotFoundException("Equipment not found");
        }else{
            equipmentDao.deleteById(equipmentId);
        }
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        Optional<EquipmentEntity> tmpEquipment = equipmentDao.findById(equipmentId);
        if(!tmpEquipment.isPresent()) {
            throw new EquipmentNotFoundException("Equipment not found");
        }else{
            tmpEquipment.get().setName(equipmentDTO.getName());
            tmpEquipment.get().setType(equipmentDTO.getType());
            tmpEquipment.get().setStatus(equipmentDTO.getStatus());
//            StaffEntity staffEntity = mapping.toStaffEntity(equipmentDTO.getAssigned_staff());
//            tmpEquipment.get().setAssigned_staff(staffEntity);
//            FieldEntity fieldEntity = mapping.toFieldEntity(equipmentDTO.getAssigned_field());
//            tmpEquipment.get().setAssigned_field(fieldEntity);
        }
    }


}
