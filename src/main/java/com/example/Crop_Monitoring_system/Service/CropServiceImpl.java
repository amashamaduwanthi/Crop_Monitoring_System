package com.example.Crop_Monitoring_system.Service;

import com.example.Crop_Monitoring_system.Exception.DataPersistException;
import com.example.Crop_Monitoring_system.dao.CropDao;
import com.example.Crop_Monitoring_system.dto.impl.CropDTO;
import com.example.Crop_Monitoring_system.entity.impl.CropEntity;
import com.example.Crop_Monitoring_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CropServiceImpl implements CropService{
    @Autowired
    private Mapping mapping;
    @Autowired
    private CropDao cropDao;
    @Override
    public void saveCrop(CropDTO cropDTO) {
        CropEntity cropEntity = cropDao.save(mapping.toCropEntity(cropDTO));
        if(cropEntity==null){
            throw new DataPersistException("crop not found !!!");
        }
    }
}
