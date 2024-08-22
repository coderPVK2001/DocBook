package com.docbook.consultation_time.service;

import com.docbook.consultation_time.entity.ConsultationTime;
import com.docbook.consultation_time.payload.ConsultationTimeDto;
import com.docbook.consultation_time.payload.UpdateConsultationStatusDto;

import java.net.URI;
import java.util.List;

public interface ConsultationTimeService  {


    List<ConsultationTime> findAllConsultationTimeSlotsByDoctor(long doctorId);

    ConsultationTime updateConsultationTimeSlots(long doctorId, UpdateConsultationStatusDto dto);
}
