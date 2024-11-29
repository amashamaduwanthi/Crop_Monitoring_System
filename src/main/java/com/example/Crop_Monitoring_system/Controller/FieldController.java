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
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/field")
@CrossOrigin(origins = "http://localhost:63342")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(@RequestParam ("field_code") String fieldCode,
                                          @RequestParam ("field_name") String fieldName,
                                          @RequestParam ("x") int x,
                                          @RequestParam ("y") int y,
                                          @RequestParam ("extent_size") String size,
                                          @RequestPart ("field_image1") MultipartFile fieldImage1,
                                          @RequestPart ("field_image2") MultipartFile fieldImage2,
                                          @RequestPart (value = "crops[]",required = false) List<CropDTO> crops,
                                          @RequestPart (value = "staff[]",required = false) List<StaffDTO> staff
    ) {
        String base64FieldImage1 = "";
        String base64FieldImage2 = "";
        Point location = new Point(x,y);
        double extentSize = Double.parseDouble(size);

        try {
            byte[] bytesFieldImage1 = fieldImage1.getBytes();
            base64FieldImage1 = AppUtil.fieldImageOneToBase64(bytesFieldImage1);

            byte[] bytesFieldImage2 = fieldImage2.getBytes();
            base64FieldImage2 = AppUtil.fieldImageTwoToBase64(bytesFieldImage2);

//            String field_code = AppUtil.generateFieldId();

            FieldDTO buildFieldDTO = new FieldDTO();
            buildFieldDTO.setField_code(fieldCode);
            buildFieldDTO.setField_name(fieldName);
            buildFieldDTO.setLocation(location);
            buildFieldDTO.setExtent_size(extentSize);
            buildFieldDTO.setField_image1(base64FieldImage1);
            buildFieldDTO.setField_image2(base64FieldImage2);
            buildFieldDTO.setCrops(crops);
            buildFieldDTO.setAllocated_staff(staff);
            fieldService.saveField(buildFieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
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
    @GetMapping
    public ResponseEntity<List<FieldDTO>> getAllFieldId() {
        try {
            List<FieldDTO> fieldfList = fieldService.getAllFields();
            return new ResponseEntity<>(fieldfList, HttpStatus.OK);
        } catch (Exception e) {
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


