package com.example.Crop_Monitoring_system.dao;


import com.example.Crop_Monitoring_system.entity.impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldDao extends JpaRepository<FieldEntity,String> {
}
