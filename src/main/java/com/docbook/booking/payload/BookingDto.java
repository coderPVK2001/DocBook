package com.docbook.booking.payload;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class BookingDto {

    private String patientName;
    private String email;
    private String mobile;

    private LocalDate date;
    private LocalTime time;
    private LocalDateTime bookedOn;

    private String doctorName;
    private String clinicName;
    private String url;
}
