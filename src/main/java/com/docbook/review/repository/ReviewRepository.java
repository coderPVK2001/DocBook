package com.docbook.review.repository;

import com.docbook.doctor.entity.Doctor;
import com.docbook.patient.entity.Patient;
import com.docbook.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.patient=:patient and r.doctor=:doctor")
   Optional<Review> findByPatientAndDoctor(@Param("patient" ) Patient patient, @Param("doctor") Doctor doctor);
}