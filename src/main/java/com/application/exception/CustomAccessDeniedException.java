package com.application.exception;

public class CustomAccessDeniedException extends RuntimeException{

    public CustomAccessDeniedException() {
    }

    public CustomAccessDeniedException(String message) {
        super(message);
    }
}
