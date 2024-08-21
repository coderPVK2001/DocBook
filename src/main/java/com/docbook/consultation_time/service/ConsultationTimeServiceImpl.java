package com.docbook.consultation_time.service;

import com.docbook.consultation_time.entity.ConsultationTime;
import com.docbook.consultation_time.payload.ConsultationTimeDto;
import com.docbook.consultation_time.repository.ConsultationTimeRepository;
import com.docbook.doctor.entity.Doctor;
import com.docbook.doctor.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultationTimeServiceImpl implements ConsultationTimeService{


    private DoctorRepository doctorRepository;
    private ConsultationTimeRepository consultationTimeRepository;

    public ConsultationTimeServiceImpl(DoctorRepository doctorRepository, ConsultationTimeRepository consultationTimeRepository) {
        this.doctorRepository = doctorRepository;
        this.consultationTimeRepository = consultationTimeRepository;
    }


    @Override
    public List<ConsultationTime> getAllConsultationTimeSlots(ConsultationTimeDto dto, long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).get();

         return dto.getTimeSlots().stream().map(slot -> convertDtoToConsultationTime(dto.getDate(), slot.getTime(), doctor)).
                collect(Collectors.toList());



    }

    public ConsultationTime convertDtoToConsultationTime(LocalDate date , LocalTime time ,Doctor doctor){
        ConsultationTime consultationTime = new ConsultationTime();
        consultationTime.setDate(date);
        consultationTime.setTime(time);
        consultationTime.setDoctor(doctor);
        return consultationTimeRepository.save(consultationTime);
    }
}
