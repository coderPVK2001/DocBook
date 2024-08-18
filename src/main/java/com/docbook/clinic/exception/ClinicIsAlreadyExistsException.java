package com.docbook.clinic.exception;

public class ClinicIsAlreadyExistsException extends RuntimeException{
    public ClinicIsAlreadyExistsException(String message) {
        super(message);
    }
}
