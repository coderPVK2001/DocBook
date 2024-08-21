package com.docbook.consultation_time.repository;

import com.docbook.consultation_time.entity.ConsultationTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ConsultationTimeRepository extends JpaRepository<ConsultationTime, Long> {

    @Query("SELECT ct FROM ConsultationTime ct " +
            "WHERE (ct.date > :currentDate OR (ct.date = :currentDate AND ct.time >= :currentTime)) " +
            "AND ct.status = 'Available' " +
            "AND ct.doctor.id = :doctorId")
    List<ConsultationTime> findAvailableConsultationsForDoctor(@Param("currentDate") LocalDate currentDate,
                                                               @Param("currentTime") LocalTime currentTime,
                                                               @Param("doctorId") Long doctorId);
}