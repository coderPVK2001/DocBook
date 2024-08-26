package com.docbook.consultation_time.repository;

import com.docbook.consultation_time.entity.ConsultationTime;
import com.docbook.doctor.entity.Doctor;
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


    @Query("select ct from ConsultationTime ct where ct.date = :currentDate and ct.time = :currentTime and ct.doctor.id = :doctorId and (ct.status = 'Booked' or ct.status = 'Available')")
    ConsultationTime findConsultaitonTimeSlotsForUpdate(@Param("currentDate") LocalDate currentDate,
                                                        @Param("currentTime") LocalTime currentTime,
                                                        @Param("doctorId") Long doctorId);


    boolean existsByDoctorAndDateAndTime(Doctor doctor, LocalDate date, LocalTime time);


    @Query("select ct from ConsultationTime ct where ct.date = :date and ct.time = :time and ct.doctor.id = :doctorId")
    ConsultationTime findByDateAndTimeAndDoctorForBooking(@Param("date") LocalDate date, @Param("time") LocalTime time,@Param("doctorId") long doctorId);
}