package com.mendix.exceptions;


public class CustomBadDataException extends RuntimeException {

    public CustomBadDataException(String message) {
        super(message);
    }

    public CustomBadDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
