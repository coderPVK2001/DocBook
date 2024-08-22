package com.docbook.consultation_time.service;

import com.docbook.consultation_time.entity.ConsultationTime;
import com.docbook.consultation_time.repository.ConsultationTimeRepository;
import com.docbook.doctor.entity.Doctor;
import com.docbook.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class SlotCreationService {
    @Autowired
    private ConsultationTimeRepository consultationTimeRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public void createSlotsForDoctor(Doctor doctor) {


        // Get the next two days
        LocalDate firstDay = LocalDate.now().plusDays(1);
        LocalDate secondDay = LocalDate.now().plusDays(2);

        // Define the time ranges for morning and afternoon slots


        // Create slots for the first and second day
        createSlotsForDay(doctor, firstDay, doctor.getMorningStart(),doctor.getMorningEnd());
        createSlotsForDay(doctor, firstDay, doctor.getAfternoonStart(),doctor.getAfternoonEnd());
        createSlotsForDay(doctor, secondDay, doctor.getMorningStart(),doctor.getMorningEnd());
        createSlotsForDay(doctor, secondDay,doctor.getAfternoonStart(),doctor.getAfternoonEnd());
    }

    private void createSlotsForDay(Doctor doctor, LocalDate date, LocalTime startTime, LocalTime endTime) {
        LocalTime currentTime = startTime;
        while (currentTime.isBefore(endTime)) {
            // Check if a slot already exists for this doctor, date, and time
            boolean slotExists = consultationTimeRepository.existsByDoctorAndDateAndTime(doctor, date, currentTime);

            if (!slotExists) {
                // Create a new slot only if it doesn't exist
                ConsultationTime consultationTime = new ConsultationTime();
                consultationTime.setDoctor(doctor);
                consultationTime.setDate(date);
                consultationTime.setTime(currentTime);
                consultationTime.setStatus("Available");
                consultationTimeRepository.save(consultationTime);
            }

            // Increment the time by 30 minutes for the next slot
            currentTime = currentTime.plusMinutes(doctor.getSlotDuration());

        }
    }



}
