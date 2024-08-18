package com.docbook.clinic.controller;

import com.docbook.clinic.entity.Clinic;
import com.docbook.clinic.payload.ClinicDto;
import com.docbook.clinic.service.ClinicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/docbook/clinic")
public class ClinicController {
    private ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addClinic(@RequestBody ClinicDto dto) {
        return new ResponseEntity(clinicService.addClinic(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{clinicId}")
    public ResponseEntity<?> deleteClinic(@PathVariable long clinicId){
        return new ResponseEntity<>(clinicService.deleteClinic(clinicId), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchClinic(@RequestParam String clinicName){
        return new ResponseEntity<>(clinicService.searchClinic(clinicName), HttpStatus.OK);
    }
}
