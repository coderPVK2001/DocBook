package com.docbook.consultation_time.payload;
import com.docbook.doctor.entity.Doctor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Data
public class ConsultationTimeDto {


    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<TimeSlots> timeSlots;

    private LocalDate date;

    private String doctorName;

    private LocalTime time;



}
