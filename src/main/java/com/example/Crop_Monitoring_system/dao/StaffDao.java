package com.example.Crop_Monitoring_system.dao;


import com.example.Crop_Monitoring_system.entity.impl.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffDao extends JpaRepository<StaffEntity,String> {
}
