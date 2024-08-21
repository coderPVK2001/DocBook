package com.docbook.consultation_time.payload;
import com.docbook.doctor.entity.Doctor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class ConsultationTimeDto {


    private Long id;

    private List<TimeSlots> timeSlots;

    private LocalDate date;

    private Doctor doctor;

}
