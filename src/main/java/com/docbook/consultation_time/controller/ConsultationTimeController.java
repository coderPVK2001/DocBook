package com.docbook.consultation_time.controller;

import com.docbook.consultation_time.payload.ConsultationTimeDto;
import com.docbook.consultation_time.payload.UpdateConsultationStatusDto;
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

    @GetMapping("getSlots/{doctorId}")
    public ResponseEntity<?> getAllConsultationTimeSlots(@PathVariable long doctorId){
        return new ResponseEntity<>(consultationTimeService.findAllConsultationTimeSlotsByDoctor(doctorId), HttpStatus.OK);
    }


    @PutMapping("/updateSlots/{doctorId}")
    public ResponseEntity<?> updateConsultationTimeSlots(@PathVariable long doctorId, @RequestBody UpdateConsultationStatusDto dto){
        return new ResponseEntity<>(consultationTimeService.updateConsultationTimeSlots(doctorId,dto), HttpStatus.OK);

    }






}
