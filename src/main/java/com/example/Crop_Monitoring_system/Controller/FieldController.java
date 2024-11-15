package com.example.Crop_Monitoring_system.Controller;

import com.example.Crop_Monitoring_system.Exception.DataPersistException;
import com.example.Crop_Monitoring_system.Exception.FieldNotFoundException;
import com.example.Crop_Monitoring_system.Service.FieldService;
import com.example.Crop_Monitoring_system.customerStatusCode.SelectedErrorStatus;
import com.example.Crop_Monitoring_system.dto.FieldStatus;
import com.example.Crop_Monitoring_system.dto.impl.CropDTO;
import com.example.Crop_Monitoring_system.dto.impl.FieldDTO;
import com.example.Crop_Monitoring_system.dto.impl.StaffDTO;
import com.example.Crop_Monitoring_system.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    public ResponseEntity<Void> saveField(
            @RequestPart("fieldCode") String fieldCode,
            @RequestPart("fieldName") String fieldName,
            @RequestPart("extentSize") Double extentSize,
            @RequestPart("location") String location,
            @RequestPart("fieldImage_01") String fieldImage_01,
            @RequestPart("fieldImage_02") String fieldImage_02,
            @RequestPart("crop") List<CropDTO>crop,
            @RequestPart("staff") List<StaffDTO> staff
            ) {
        String base64field_image_01 = "";
        String base64field_image_02 = "";

        try {

            byte[] bytesFieldImage1 = fieldImage_01.getBytes();
            base64field_image_01 = AppUtil.fieldImageToBase64(bytesFieldImage1);

            byte[] bytesFieldImage2 = fieldImage_02.getBytes();
            base64field_image_02 = AppUtil.fieldImageToBase64(bytesFieldImage2);

            FieldDTO fieldDTO = new FieldDTO();
            fieldDTO.setField_code(fieldCode);
            fieldDTO.setField_name(fieldName);
            fieldDTO.setExtent_size(extentSize);
            fieldDTO.setLocation(location);
            fieldDTO.setField_image1(base64field_image_01);
            fieldDTO.setField_image2(base64field_image_02);
            fieldDTO.setCrops(crop);
            fieldDTO.setAllocated_staff(staff);

            fieldService.saveField(fieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        @GetMapping(value = "/{fieldCode}", produces = MediaType.APPLICATION_JSON_VALUE)
        public FieldStatus getSelectedField(@PathVariable("fieldCode") String fieldCode) {

        return fieldService.getField(fieldCode);
    }
        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public List<FieldDTO> getAllFields() {
        return fieldService.getAllFields();
    }
        @DeleteMapping(value = "/{fieldCode}")
        public ResponseEntity<Void> deleteField(@PathVariable("fieldCode") String fieldCode) {
        try {

            fieldService.deleteField(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
        @PutMapping(value = "/{fieldCode}")
        public ResponseEntity<Void> updateField(@PathVariable ("fieldCode") String fieldCode,
            @RequestBody FieldDTO fieldDTO) {

            try {

                fieldService.updateField(fieldCode, fieldDTO);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (FieldNotFoundException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

}
