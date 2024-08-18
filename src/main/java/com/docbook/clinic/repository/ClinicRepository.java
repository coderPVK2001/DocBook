package com.docbook.clinic.repository;

import com.docbook.clinic.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    Optional<Clinic> findByClinicName(String clinicName);
}