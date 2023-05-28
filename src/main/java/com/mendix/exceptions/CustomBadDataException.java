package com.mendix.exceptions;

public class CustomBadDataException extends Exception {

    public CustomBadDataException(String message) {
        super(message);
    }

    public CustomBadDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
