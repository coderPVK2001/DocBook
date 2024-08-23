package com.docbook.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingPatient {


    @ExceptionHandler(PatientIdNotFoundException.class)
    public ResponseEntity<?> patientException( PatientIdNotFoundException patientIdNotFoundException){

        return new ResponseEntity<>(patientIdNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
