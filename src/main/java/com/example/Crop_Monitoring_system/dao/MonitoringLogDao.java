package com.example.Crop_Monitoring_system.dao;


import com.example.Crop_Monitoring_system.entity.impl.MonitoringLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoringLogDao extends JpaRepository<MonitoringLogEntity,String> {
}
