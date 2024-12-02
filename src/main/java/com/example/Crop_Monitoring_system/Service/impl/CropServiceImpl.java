package com.example.Crop_Monitoring_system.Service.impl;

import com.example.Crop_Monitoring_system.Exception.CropNotFoundException;
import com.example.Crop_Monitoring_system.Exception.DataPersistException;
import com.example.Crop_Monitoring_system.Exception.FieldNotFoundException;
import com.example.Crop_Monitoring_system.Service.CropService;
import com.example.Crop_Monitoring_system.customerStatusCode.SelectedErrorStatus;
import com.example.Crop_Monitoring_system.dao.CropDao;
import com.example.Crop_Monitoring_system.dao.FieldDao;
import com.example.Crop_Monitoring_system.dto.CropStatus;
import com.example.Crop_Monitoring_system.dto.impl.CropDTO;
import com.example.Crop_Monitoring_system.dto.impl.FieldDTO;
import com.example.Crop_Monitoring_system.entity.impl.CropEntity;
import com.example.Crop_Monitoring_system.entity.impl.FieldEntity;
import com.example.Crop_Monitoring_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CropServiceImpl implements CropService {
    @Autowired
    private Mapping mapping;
    @Autowired
    private CropDao cropDao;
    @Autowired
    private FieldDao fieldDao;
    @Override
    public void saveCrop(CropDTO cropDTO) {
        CropEntity cropEntity = cropDao.save(mapping.toCropEntity(cropDTO));
        if(cropEntity==null){
            throw new DataPersistException("crop not found !!!");
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        List<CropEntity> crops = cropDao.findAll();
        return crops.stream()
                .map(crop -> {
                    CropDTO cropDTO = new CropDTO();
                    cropDTO.setCrop_code(crop.getCrop_code());
                    cropDTO.setCrop_image(crop.getCrop_image());
                    cropDTO.setCommon_name(crop.getCommon_name());
                    cropDTO.setScientific_name(crop.getScientific_name());
                    cropDTO.setCategory(crop.getCategory());
                    cropDTO.setSeason(crop.getSeason());
                    Optional<FieldEntity> assignedField = fieldDao.
                            findById(crop.getField().getField_code());
                    FieldDTO assignedFieldDTO = assignedField.isPresent() ?
                            mapping.toFieldDTO(assignedField.get()) : null;
                    cropDTO.setField(assignedFieldDTO);
                    return cropDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CropStatus getCrop(String cropCode) {
        if(cropDao.existsById(cropCode)) {
            var selectedCrop = cropDao.getReferenceById(cropCode);
            return mapping.toCropDTO(selectedCrop);
        }else {
            return new SelectedErrorStatus(2,"Selected crop does not exist");
        }
    }

    @Override
    public void deleteCrop(String crop_code) {
        Optional<CropEntity> foundCrop = cropDao.findById(crop_code);
        if(!foundCrop.isPresent()) {
            throw new CropNotFoundException("Crop not found");
        }else {
            cropDao.deleteById(crop_code);
        }

    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) {
        Optional<CropEntity> tmpCrop = cropDao.findById(cropCode);
        if(!tmpCrop.isPresent()) {
            throw new CropNotFoundException("Crop not found");
        }else {
            tmpCrop.get().setCommon_name(cropDTO.getCommon_name());
            tmpCrop.get().setScientific_name(cropDTO.getScientific_name());
            tmpCrop.get().setCrop_image(cropDTO.getCrop_image());
            tmpCrop.get().setCategory(cropDTO.getCategory());
            tmpCrop.get().setSeason(cropDTO.getSeason());
           // tmpCrop.get().setSeason(String.valueOf(cropDTO.getField()));
          //  tmpCrop.get().setSeason(String.valueOf(cropDTO.getCrop_code()));
        }

    }

//    @Override
//    public CropDTO getCropByName(String cropCode) {
//        Optional<CropEntity> tmpField = cropDao.findByCropName(cropCode);
//        if(!tmpField.isPresent()){
//            throw new CropNotFoundException("Crop not found: " + cropCode);
//        }
//        return mapping.toCropDTO(tmpField.get());
//    }


}
