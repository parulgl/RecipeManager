package com.mendix.exceptions;

public class CustomDataNotFoundException extends Exception {

    public CustomDataNotFoundException(String message) {
        super(message);
    }

    public CustomDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
