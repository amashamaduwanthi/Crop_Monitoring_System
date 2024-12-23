package com.example.Crop_Monitoring_system.Service;

import com.example.Crop_Monitoring_system.dto.MonitoringLogStatus;
import com.example.Crop_Monitoring_system.dto.impl.MonitoringLogDTO;

import java.util.List;

public interface MonitoringLogService {
    void saveLog(MonitoringLogDTO monitoringLogDTO);

    List<MonitoringLogDTO> getAllLogs();

    MonitoringLogStatus getSelectedLogId(String logCode);

    void deleteLog(String logCode);

    void updateLog(String logCode, MonitoringLogDTO monitoringLogDTO);
}
