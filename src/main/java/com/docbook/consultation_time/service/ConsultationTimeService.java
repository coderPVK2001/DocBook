package com.docbook.consultation_time.service;

import com.docbook.consultation_time.entity.ConsultationTime;
import com.docbook.consultation_time.payload.ConsultationTimeDto;

import java.net.URI;
import java.util.List;

public interface ConsultationTimeService  {

    List<ConsultationTimeDto> getAllConsultationTimeSlots(ConsultationTimeDto dto, long doctorId);

    List<ConsultationTime> findAllConsultationTimeSlotsByDoctor(long doctorId);
}
