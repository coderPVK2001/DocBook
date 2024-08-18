package com.docbook.clinic.entity;

import com.docbook.area.entity.Area;
import com.docbook.city.entity.City;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clinic")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "clinic_name")
    private String clinicName;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;




}