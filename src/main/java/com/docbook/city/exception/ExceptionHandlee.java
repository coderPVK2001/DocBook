package com.docbook.city.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlee {

    @ExceptionHandler(NoCityFoundException.class)
    public ResponseEntity<?> handleCityException(
            NoCityFoundException noCityFoundException
    ){
        return new ResponseEntity(noCityFoundException.getMessage(),HttpStatus.BAD_REQUEST);
    }
}


