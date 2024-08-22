package com.docbook.consultation_time.service;
import com.docbook.consultation_time.entity.ConsultationTime;
import com.docbook.consultation_time.payload.ConsultationTimeDto;
import com.docbook.consultation_time.payload.UpdateConsultationStatusDto;
import com.docbook.consultation_time.repository.ConsultationTimeRepository;
import com.docbook.doctor.entity.Doctor;
import com.docbook.doctor.repository.DoctorRepository;
import org.springframework.stereotype.Service;
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
    public List<ConsultationTime> findAllConsultationTimeSlotsByDoctor(long doctorId) {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        return consultationTimeRepository.findAvailableConsultationsForDoctor(currentDate, currentTime, doctorId);

    }

    @Override
    public ConsultationTime updateConsultationTimeSlots(long doctorId, UpdateConsultationStatusDto dto) {
        ConsultationTime consultationTime = consultationTimeRepository.findConsultaitonTimeSlotsForUpdate(dto.getDate(), dto.getTime(), doctorId);
        consultationTime.setStatus(dto.getStatus());
        return consultationTimeRepository.save(consultationTime);
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
