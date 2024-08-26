package com.docbook.Report.repository;

import com.docbook.Report.entity.Report;
import com.docbook.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {


    @Query("select r from Report r where r.patient=:patient")
    List<Report> findAllByPatient(@Param("patient") Patient patient);
}