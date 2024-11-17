package com.example.Crop_Monitoring_system.Service.impl;

import com.example.Crop_Monitoring_system.Exception.DataPersistException;
import com.example.Crop_Monitoring_system.Service.MonitoringLogService;
import com.example.Crop_Monitoring_system.customerStatusCode.SelectedErrorStatus;
import com.example.Crop_Monitoring_system.dao.MonitoringLogDao;
import com.example.Crop_Monitoring_system.dto.MonitoringLogStatus;
import com.example.Crop_Monitoring_system.dto.impl.MonitoringLogDTO;
import com.example.Crop_Monitoring_system.entity.impl.MonitoringLogEntity;
import com.example.Crop_Monitoring_system.util.AppUtil;
import com.example.Crop_Monitoring_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
