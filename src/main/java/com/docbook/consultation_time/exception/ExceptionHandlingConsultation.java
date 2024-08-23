package com.docbook.consultation_time.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingConsultation {

    @ExceptionHandler(ConsultationIdNotFoundException.class)
    public ResponseEntity<?> consultationException( ConsultationIdNotFoundException consultationIdNotFoundException){

        return new ResponseEntity<>(consultationIdNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
