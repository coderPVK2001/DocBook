package com.docbook.specializations.repository;

import com.docbook.specializations.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    Optional<Specialization> findByExpertise(String expertise);
}