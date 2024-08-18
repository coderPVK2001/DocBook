package com.docbook.clinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClinicExceptionHandler {

    @ExceptionHandler(ClinicIsAlreadyExistsException.class)
    public ResponseEntity<?> clinicAlreadyExists(ClinicIsAlreadyExistsException ex){
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClinicNotFoundException.class)
    public ResponseEntity<?> clinicNotFound(ClinicNotFoundException ex){
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
