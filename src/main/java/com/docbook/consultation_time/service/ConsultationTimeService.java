package com.docbook.consultation_time.service;

import com.docbook.consultation_time.entity.ConsultationTime;
import com.docbook.consultation_time.payload.ConsultationTimeDto;

import java.net.URI;
import java.util.List;

public interface ConsultationTimeService  {

    List<ConsultationTime> getAllConsultationTimeSlots(ConsultationTimeDto dto, long doctorId);
}
