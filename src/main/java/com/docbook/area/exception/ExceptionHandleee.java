package com.docbook.area.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandleee {

    @ExceptionHandler(AreaNotFoundException.class)
   ResponseEntity<?> areaexception(
           AreaNotFoundException areaNotFoundException
   ) {

        return new ResponseEntity<>(areaNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
   }
}
