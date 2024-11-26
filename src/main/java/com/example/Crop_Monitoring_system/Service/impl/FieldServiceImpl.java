package com.example.Crop_Monitoring_system.Service.impl;

import com.example.Crop_Monitoring_system.Exception.FieldNotFoundException;
import com.example.Crop_Monitoring_system.Service.FieldService;
import com.example.Crop_Monitoring_system.customerStatusCode.SelectedErrorStatus;
import com.example.Crop_Monitoring_system.dao.FieldDao;
import com.example.Crop_Monitoring_system.dto.FieldStatus;
import com.example.Crop_Monitoring_system.dto.impl.FieldDTO;
import com.example.Crop_Monitoring_system.entity.impl.CropEntity;
import com.example.Crop_Monitoring_system.entity.impl.FieldEntity;
import com.example.Crop_Monitoring_system.entity.impl.StaffEntity;
import com.example.Crop_Monitoring_system.util.AppUtil;
import com.example.Crop_Monitoring_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveField(FieldDTO fieldDTO) {
        fieldDTO.setField_code(AppUtil.generateFieldId());
        FieldEntity fieldEntity = fieldDao.save(mapping.toFieldEntity(fieldDTO));
        if (fieldEntity == null) {
            throw new FieldNotFoundException("Field not found");
        }
    }
    @Override
    public List<FieldDTO> getAllFields() {
        return mapping.toFieldDTOList(fieldDao.findAll());
    }

    @Override
    public FieldStatus getField(String fieldCode) {
        if(fieldDao.existsById(fieldCode)){
            var selectedField = fieldDao.getReferenceById(fieldCode);
            return mapping.toFieldDTO(selectedField);
        }else {
            return new SelectedErrorStatus(2,"Selected field not found");
        }
    }

    @Override
    public void deleteField(String fieldCode) {
        Optional<FieldEntity> foundField = fieldDao.findById(fieldCode);
        if(foundField.isPresent()){
            throw new FieldNotFoundException("Field not found");
        }else {
            fieldDao.deleteById(fieldCode);
        }
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {
        Optional<FieldEntity> tmpField = fieldDao.findById(fieldCode);
        if(!tmpField.isPresent()){
            throw new FieldNotFoundException("Field not found");
        }else{
            tmpField.get().setField_name(fieldDTO.getField_name());
            tmpField.get().setLocation(fieldDTO.getLocation());
            tmpField.get().setExtent_size(fieldDTO.getExtent_size());
            tmpField.get().setField_image1(fieldDTO.getField_image1());
            tmpField.get().setField_image2(fieldDTO.getField_image2());
//            List<CropEntity> cropEntityList = mapping.toCropEntityList(fieldDTO.getCrops());
//            tmpField.get().setCrops(cropEntityList);
//            List<StaffEntity> staffEntityList = mapping.toStaffEntityList(fieldDTO.getAllocated_staff());
//            tmpField.get().setAllocated_staff(staffEntityList);
        }
    }

    @Override
    public FieldDTO getFieldByName(String field_code) {
        Optional<FieldEntity> tmpField = fieldDao.findByFieldName(field_code);
        if(!tmpField.isPresent()){
            throw new FieldNotFoundException("Field not found: " + field_code);
        }
        return mapping.toFieldDTO(tmpField.get());
    }

}
