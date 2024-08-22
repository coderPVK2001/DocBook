package com.docbook.patient.controller;

import com.docbook.patient.payload.PatientDto;
import com.docbook.patient.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/docbook/patient")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPatientDetails(@Valid @RequestBody PatientDto patientDto ,
                                               BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        PatientDto patientDto1 = patientService.addDetails(patientDto);
        return new ResponseEntity<>(patientDto1, HttpStatus.CREATED);
    }


}
