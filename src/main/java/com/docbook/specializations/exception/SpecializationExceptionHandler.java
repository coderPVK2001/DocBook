package com.docbook.specializations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SpecializationExceptionHandler {

    @ExceptionHandler(SpecializationAlreadyPresent.class)
    public ResponseEntity<?> specializationAlreadyPresent(SpecializationAlreadyPresent ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SpecializationNotFound.class)
    public ResponseEntity<?> specializationNotFound(SpecializationNotFound ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
