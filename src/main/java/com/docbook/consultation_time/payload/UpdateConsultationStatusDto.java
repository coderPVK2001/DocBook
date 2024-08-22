package com.docbook.consultation_time.payload;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class UpdateConsultationStatusDto {

    private String status;
    private LocalDate date;
    private LocalTime time;

}
