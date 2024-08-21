package com.docbook.consultation_time.payload;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TimeSlots {

    private LocalTime time;
}
