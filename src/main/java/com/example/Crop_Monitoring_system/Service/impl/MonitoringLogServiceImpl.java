package com.example.Crop_Monitoring_system.Service.impl;

import com.example.Crop_Monitoring_system.Exception.DataPersistException;
import com.example.Crop_Monitoring_system.Exception.MonitoringLogNotFoundException;
import com.example.Crop_Monitoring_system.Service.MonitoringLogService;
import com.example.Crop_Monitoring_system.customerStatusCode.SelectedErrorStatus;
import com.example.Crop_Monitoring_system.dao.MonitoringLogDao;
import com.example.Crop_Monitoring_system.dto.MonitoringLogStatus;
import com.example.Crop_Monitoring_system.dto.impl.MonitoringLogDTO;
import com.example.Crop_Monitoring_system.entity.impl.CropEntity;
import com.example.Crop_Monitoring_system.entity.impl.FieldEntity;
import com.example.Crop_Monitoring_system.entity.impl.MonitoringLogEntity;
import com.example.Crop_Monitoring_system.entity.impl.StaffEntity;
import com.example.Crop_Monitoring_system.util.AppUtil;
import com.example.Crop_Monitoring_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MonitoringLogServiceImpl implements MonitoringLogService {
    @Autowired
    private MonitoringLogDao monitoringLogDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveLog(MonitoringLogDTO monitoringLogDTO) {
        monitoringLogDTO.setLog_code(AppUtil.generateMonitoringId());
        MonitoringLogEntity save = monitoringLogDao.save(mapping.toMonitoringLogEntity(monitoringLogDTO));
        if(save==null){
            throw new DataPersistException("Monitoring Log not saved");
        }
    }

    @Override
    public List<MonitoringLogDTO> getAllLogs() {
       return mapping.toMonitoringLogDTOList(monitoringLogDao.findAll());
    }

    @Override
    public MonitoringLogStatus getSelectedLogId(String logCode) {
        if(monitoringLogDao.existsById(logCode)){
            var selectedLog = monitoringLogDao.getReferenceById(logCode);
            return mapping.toMonitoringLogDTO(selectedLog);
        }else{
            return new SelectedErrorStatus(2,"Selected Log not found");
        }
    }

    @Override
    public void deleteLog(String logCode) {
        Optional<MonitoringLogEntity> foundById = monitoringLogDao.findById(logCode);
        if(foundById.isPresent()){
            throw new MonitoringLogNotFoundException("Log not Found");
        }else {
            monitoringLogDao.deleteById(logCode);
        }
    }

    @Override
    public void updateLog(String logCode, MonitoringLogDTO monitoringLogDTO) {
        Optional<MonitoringLogEntity> byId = monitoringLogDao.findById(logCode);
        if(byId.isPresent()){
            throw new MonitoringLogNotFoundException("Log not found");
        }else {
            byId.get().setLog_date(monitoringLogDTO.getLog_date());
            byId.get().setLog_details(monitoringLogDTO.getLog_details());
            byId.get().setObserved_image(monitoringLogDTO.getObserved_image());
//            List<FieldEntity>fieldEntityList=mapping.toFieldEntityList(monitoringLogDTO.getFields());
//            byId.get().setFields(fieldEntityList);
//            List<CropEntity>cropEntityList=mapping.toCropEntityList(monitoringLogDTO.getCrops());
//            byId.get().setCrops(cropEntityList);
//            List<StaffEntity>staffEntityList=mapping.toStaffEntityList(monitoringLogDTO.getStaff());
//            byId.get().setStaff(staffEntityList);
        }
    }
}
