package com.docbook.clinic.exception;

public class ClinicNotFoundException extends RuntimeException{
    public ClinicNotFoundException(String message) {
        super(message);
    }
}
