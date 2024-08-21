package com.docbook.consultation_time.controller;

import com.docbook.consultation_time.payload.ConsultationTimeDto;
import com.docbook.consultation_time.service.ConsultationTimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dockbook/consultation-time")
public class ConsultationTimeController {

    private ConsultationTimeService consultationTimeService;
    public ConsultationTimeController(ConsultationTimeService consultationTimeService) {
        this.consultationTimeService = consultationTimeService;
    }
    @PostMapping("/addSlots")
    public ResponseEntity<?> getConsultationTimeSlots(@RequestBody ConsultationTimeDto dto, @PathVariable long doctorId){
        return new ResponseEntity<>(consultationTimeService.getAllConsultationTimeSlots(dto,doctorId), HttpStatus.CREATED);
    }
}
