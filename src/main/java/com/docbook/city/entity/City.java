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

//    public String getCityname() {
//        return cityname;
//    }
//
//    public void setCityname(String cityname) {
//        this.cityname = cityname;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
}