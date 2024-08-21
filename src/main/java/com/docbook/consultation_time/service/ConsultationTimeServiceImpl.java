package com.docbook.consultation_time.service;

import com.docbook.consultation_time.entity.ConsultationTime;
import com.docbook.consultation_time.payload.ConsultationTimeDto;
import com.docbook.consultation_time.payload.TimeSlots;
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
    public List<ConsultationTimeDto> getAllConsultationTimeSlots(ConsultationTimeDto dto, long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).get();
        List<ConsultationTime> listOfConsultation = dto.getTimeSlots().stream().map(slot -> convertDtoToConsultationTime(dto.getDate(), slot.getTime(), doctor)).
                collect(Collectors.toList());
        return listOfConsultation.stream().map(x -> consultationTimeEntityToDto(x)).toList();
    }

    @Override
    public List<ConsultationTime> findAllConsultationTimeSlotsByDoctor(long doctorId) {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        return consultationTimeRepository.findAvailableConsultationsForDoctor(currentDate, currentTime, doctorId);

    }

    public ConsultationTime convertDtoToConsultationTime(LocalDate date , LocalTime time ,Doctor doctor){
        ConsultationTime consultationTime = new ConsultationTime();
        consultationTime.setDate(date);
        consultationTime.setTime(time);
        consultationTime.setDoctor(doctor);
        consultationTime.setStatus("Available");
        return consultationTimeRepository.save(consultationTime);
    }

    public ConsultationTimeDto consultationTimeEntityToDto(ConsultationTime dto){
        ConsultationTimeDto consultationTime = new ConsultationTimeDto();
        consultationTime.setDate(dto.getDate());
        consultationTime.setId(dto.getId());
        consultationTime.setTime(dto.getTime());
        consultationTime.setDoctorName(dto.getDoctor().getDoctorName());
        return consultationTime;
    }
}
