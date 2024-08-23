package com.docbook.patient.exception;

public class PatientIdNotFoundException extends RuntimeException{

    public PatientIdNotFoundException(String message) {
        super(message);
    }
}
