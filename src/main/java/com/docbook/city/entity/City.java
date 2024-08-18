package com.docbook.city.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cityname")
    private String cityname;

}