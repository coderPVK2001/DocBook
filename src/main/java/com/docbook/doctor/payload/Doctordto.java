package com.docbook.doctor.payload;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Doctordto {

    private String name;
    private String mobile;
    private String email;
    private String url;

    private String areaname;
    private String specialization;
    private String clinic;
    private String city;

    private int slotDuration;
    private LocalTime afternoonEnd;
    private LocalTime morningStart;
    private LocalTime afternoonStart;
    private LocalTime morningEnd;
}
