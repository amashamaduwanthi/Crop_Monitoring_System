package com.example.Crop_Monitoring_system.dao;


import com.example.Crop_Monitoring_system.entity.impl.FieldEntity;
import com.example.Crop_Monitoring_system.entity.impl.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StaffDao extends JpaRepository<StaffEntity,String> {
//    @Query("SELECT s FROM StaffEntity s WHERE s.id = :id")
//    Optional<StaffEntity> findByStaffName(@Param("id") String id);
}
