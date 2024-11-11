package com.example.Crop_Monitoring_system.Controller;

import com.example.Crop_Monitoring_system.Exception.DataPersistException;
import com.example.Crop_Monitoring_system.Service.CropService;
import com.example.Crop_Monitoring_system.dto.impl.CropDTO;
import com.example.Crop_Monitoring_system.dto.impl.FieldDTO;
import com.example.Crop_Monitoring_system.dto.impl.StaffDTO;
import com.example.Crop_Monitoring_system.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/crop")
public class CropController {
    @Autowired
    private CropService cropService;
    public ResponseEntity<Void> saveField(
            @RequestPart("cropCode") String cropCode,
            @RequestPart("commonName") String commonName,
            @RequestPart("scienceName") String scienceName,
            @RequestPart("category") String category,
            @RequestPart("season") String season,
            @RequestPart("cropImage") String cropImage,
            @RequestPart("field") FieldDTO field
    )
    {
        String base64Crop_image="";
        try {
            byte[] bytesCropImage = cropImage.getBytes();
            base64Crop_image = AppUtil.cropImageToBase64(bytesCropImage);

            CropDTO cropDTO = new CropDTO();
            cropDTO.setCrop_code(cropCode);
            cropDTO.setCommon_name(commonName);
            cropDTO.setScientific_name(scienceName);
            cropDTO.setCategory(category);
            cropDTO.setSeason(season);
            cropDTO.setCrop_image(base64Crop_image);
            cropDTO.setField(field);

            cropService.saveCrop(cropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
