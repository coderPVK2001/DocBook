package com.docbook.specializations.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "specialization")
public class Specialization {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "expertise", nullable = false, unique = true, length = 200)
    private String expertise;

}