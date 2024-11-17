package com.example.Crop_Monitoring_system.Controller;

import com.example.Crop_Monitoring_system.Exception.DataPersistException;
import com.example.Crop_Monitoring_system.Service.MonitoringLogService;
import com.example.Crop_Monitoring_system.dto.impl.CropDTO;
import com.example.Crop_Monitoring_system.dto.impl.FieldDTO;
import com.example.Crop_Monitoring_system.dto.impl.MonitoringLogDTO;
import com.example.Crop_Monitoring_system.dto.impl.StaffDTO;
import com.example.Crop_Monitoring_system.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/log")
public class MonitoringLogController {
    @Autowired
    private MonitoringLogService monitoringLogService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveLog(@RequestPart ("logDate")String logDate,
                                        @RequestPart ("logDetails")String logDetails,
                                        @RequestPart ("observedImage")MultipartFile observedImage,
                                        @RequestPart ("field")List<FieldDTO> fields,
                                        @RequestPart ("crops") List<CropDTO> crops,
                                        @RequestPart ("staff") List<StaffDTO> staff
                                        ) {
        String base64ObservedImage="";
        try{
            byte[] bytesObservedImage = observedImage.getBytes();
           base64ObservedImage = AppUtil.observedImageOneToBase64(bytesObservedImage);
            String monitoringId = AppUtil.generateMonitoringId();
            MonitoringLogDTO monitoringLogDTO = new MonitoringLogDTO();
            monitoringLogDTO.setLog_code(monitoringId);
            monitoringLogDTO.setLog_date(logDate);
            monitoringLogDTO.setLog_details(logDetails);
            monitoringLogDTO.setObserved_image(base64ObservedImage);
            monitoringLogDTO.setFields(fields);
            monitoringLogDTO.setCrops(crops);
            monitoringLogDTO.setStaff(staff);
            monitoringLogService.saveLog(monitoringLogDTO);
            return  new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
          e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitoringLogDTO> getAllLogs(){
     return monitoringLogService.getAllLogs();
    }
}
