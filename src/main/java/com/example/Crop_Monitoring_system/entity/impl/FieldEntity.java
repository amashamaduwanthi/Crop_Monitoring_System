package com.example.Crop_Monitoring_system.entity.impl;

import com.example.Crop_Monitoring_system.entity.SuperEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Field")
public class FieldEntity implements SuperEntity {
    @Id
    private String field_code;
    private String field_name;
    private Point location;
    private Double extent_size;
    @Column(columnDefinition = "LONGTEXT")
    private String field_image1;
    @Column(columnDefinition = "LONGTEXT")
    private String field_image2;
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CropEntity> crops;
    // List of equipment related to the field, cascading delete included
//    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<EquipmentEntity> equipment;

    @ManyToMany(mappedBy = "fields")
    private List<StaffEntity> allocated_staff;
}
