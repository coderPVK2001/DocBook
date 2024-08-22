package com.docbook.consultation_time.config;

import com.docbook.consultation_time.service.SlotCreationService;
import com.docbook.doctor.entity.Doctor;
import com.docbook.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    private SlotCreationService slotCreationService;

    private DoctorRepository doctorRepository;

    public SchedulerConfig(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Runs every day at midnight
    public void scheduleSlotCreationForAllDoctors() {


        /*
        for 30 minutes slots

         */
        List<Doctor> allDoctors = doctorRepository.findAll();


        for (Doctor doctor : allDoctors) {
            slotCreationService.createSlotsForDoctor(doctor);


        }


    }

}
