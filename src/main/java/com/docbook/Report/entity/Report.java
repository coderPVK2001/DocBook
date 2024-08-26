package com.docbook.Report.entity;

import com.docbook.patient.entity.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "report_name", nullable = false, length = 100)
    private String reportName;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "generated_at")
    private LocalDateTime generatedAt;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}