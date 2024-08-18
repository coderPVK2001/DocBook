package com.docbook.city.exception;

public class NoCityFoundException extends RuntimeException{

    public NoCityFoundException(String message) {
        super(message);
    }
}
