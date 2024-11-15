package com.example.Crop_Monitoring_system.Service.impl;

import com.example.Crop_Monitoring_system.Exception.FieldNotFoundException;
import com.example.Crop_Monitoring_system.Service.FieldService;
import com.example.Crop_Monitoring_system.dao.FieldDao;
import com.example.Crop_Monitoring_system.dto.impl.FieldDTO;
import com.example.Crop_Monitoring_system.entity.impl.FieldEntity;
import com.example.Crop_Monitoring_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveField(FieldDTO fieldDTO) {
        FieldEntity fieldEntity = fieldDao.save(mapping.toFieldEntity(fieldDTO));
        if(fieldEntity==null){
            throw new FieldNotFoundException("field not found !!!");
        }
    }
}
