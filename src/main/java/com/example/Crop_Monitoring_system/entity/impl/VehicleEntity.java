package com.example.Crop_Monitoring_system.entity.impl;

import com.example.Crop_Monitoring_system.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "vehicle")
public class VehicleEntity implements SuperEntity {
    @Id
    private String vehicle_code;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String remarks;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private StaffEntity assigned_staff;
}
