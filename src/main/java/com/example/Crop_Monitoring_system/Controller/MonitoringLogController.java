package com.example.Crop_Monitoring_system.Controller;

import com.example.Crop_Monitoring_system.Exception.DataPersistException;
import com.example.Crop_Monitoring_system.Exception.MonitoringLogNotFoundException;
import com.example.Crop_Monitoring_system.Service.CropService;
import com.example.Crop_Monitoring_system.Service.FieldService;
import com.example.Crop_Monitoring_system.Service.MonitoringLogService;
import com.example.Crop_Monitoring_system.Service.StaffService;
import com.example.Crop_Monitoring_system.dto.MonitoringLogStatus;
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
@CrossOrigin(origins = "http://localhost:63342")
public class MonitoringLogController {
    @Autowired
    private MonitoringLogService monitoringLogService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveLog(@RequestParam ("logCode")String logCode,
                                        @RequestParam ("logDate")String logDate,
                                        @RequestParam ("logDetails")String logDetails,
                                        @RequestPart ("observedImage")MultipartFile observedImage
//                                        @RequestPart (value = "fields[]",required = false) List<FieldDTO> fields,
//                                        @RequestPart (value = "crops[]",required = false) List<CropDTO> crops,
//                                        @RequestPart (value = "staff[]",required = false) List<StaffDTO> staff
                                        ) {
        String base64ObservedImage="";
        try{
            byte[] bytesObservedImage = observedImage.getBytes();
           base64ObservedImage = AppUtil.observedImageOneToBase64(bytesObservedImage);
//           String monitoringId = AppUtil.generateMonitoringId();
            MonitoringLogDTO monitoringLogDTO = new MonitoringLogDTO();
            monitoringLogDTO.setLog_code(logCode);
            monitoringLogDTO.setLog_date(logDate);
            monitoringLogDTO.setLog_details(logDetails);
            monitoringLogDTO.setObserved_image(base64ObservedImage);
//            monitoringLogDTO.setFields(fields);
//            monitoringLogDTO.setCrops(crops);
//            monitoringLogDTO.setStaff(staff);
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
    @GetMapping(value =" /{logCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitoringLogStatus getSelectedLogId(@PathVariable ("logCode") String logCode){
        return monitoringLogService.getSelectedLogId(logCode);
    }
    @DeleteMapping(value = "/{logCode}")
    public ResponseEntity<Void> deleteLog(@PathVariable("logCode") String logCode){
        try {
            monitoringLogService.deleteLog(logCode);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(MonitoringLogNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping(value = "/{logCode}")
    public ResponseEntity<Void> updateLog(@PathVariable("logCode")String logCode,@RequestBody MonitoringLogDTO monitoringLogDTO){
        try {
            monitoringLogService.updateLog(logCode,monitoringLogDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (MonitoringLogNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
