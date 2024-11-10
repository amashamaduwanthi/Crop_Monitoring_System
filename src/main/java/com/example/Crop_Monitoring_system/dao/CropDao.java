package com.example.Crop_Monitoring_system.dao;


import com.example.Crop_Monitoring_system.entity.impl.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropDao extends JpaRepository<CropEntity,String> {
}
