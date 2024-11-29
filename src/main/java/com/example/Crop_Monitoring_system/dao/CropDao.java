package com.example.Crop_Monitoring_system.dao;


import com.example.Crop_Monitoring_system.entity.impl.CropEntity;
import com.example.Crop_Monitoring_system.entity.impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CropDao extends JpaRepository<CropEntity,String> {
//    @Query("SELECT c FROM CropEntity c WHERE c.crop_code = :crop_code")
//    Optional<CropEntity> findByCropName(@Param("crop_code") String crop_code);
}
