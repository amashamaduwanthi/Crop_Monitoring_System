package com.example.Crop_Monitoring_system.dao;


import com.example.Crop_Monitoring_system.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByEmail(String email);
}
