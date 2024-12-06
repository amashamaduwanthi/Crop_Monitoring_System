package com.example.Crop_Monitoring_system.Controller;

import com.example.Crop_Monitoring_system.Exception.DataPersistException;
import com.example.Crop_Monitoring_system.Exception.EquipmentNotFoundException;
import com.example.Crop_Monitoring_system.Service.EquipmentService;
import com.example.Crop_Monitoring_system.dto.EquipmentStatus;
import com.example.Crop_Monitoring_system.dto.impl.EquipmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/equipment")
@CrossOrigin(origins = "http://localhost:63342")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        try {
            equipmentService.saveEquipment(equipmentDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{equipmentId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentStatus getSelectedEquipment(@PathVariable ("equipmentId") String equipmentId) {
        return equipmentService.getEquipment(equipmentId);
    }
    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipmentDTO> getAllEquipments() {
        return equipmentService.getAllEquipment();
    }
    @DeleteMapping(value = "/{equipmentId}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable("equipmentId") String equipmentId) {
        try {
            equipmentService.deleteEquipment(equipmentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (EquipmentNotFoundException e){
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{equipmentId}")
    public ResponseEntity<Void> updateEquipment(@PathVariable("equipmentId") String equipmentId,
                                                @RequestBody EquipmentDTO equipmentDTO) {

        try {

            equipmentService.updateEquipment(equipmentId, equipmentDTO);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EquipmentNotFoundException e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
