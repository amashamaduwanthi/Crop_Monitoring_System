package com.example.Crop_Monitoring_system.dao;


import com.example.Crop_Monitoring_system.entity.impl.FieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FieldDao extends JpaRepository<FieldEntity,String> {
    @Query("SELECT f FROM FieldEntity f WHERE f.field_code = :field_code")
    Optional<FieldEntity> findByFieldName(@Param("field_code") String field_code);

}
