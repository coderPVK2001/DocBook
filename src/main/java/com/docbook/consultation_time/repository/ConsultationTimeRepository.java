package com.docbook.consultation_time.repository;

import com.docbook.consultation_time.entity.ConsultationTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationTimeRepository extends JpaRepository<ConsultationTime, Long> {
}