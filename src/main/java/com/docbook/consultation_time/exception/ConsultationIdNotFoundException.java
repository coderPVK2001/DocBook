package com.docbook.consultation_time.exception;

public class ConsultationIdNotFoundException extends RuntimeException {
    public ConsultationIdNotFoundException(String message) {
        super(message);
    }
}
