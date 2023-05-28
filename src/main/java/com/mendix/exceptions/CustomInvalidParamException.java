package com.mendix.exceptions;

public class CustomInvalidParamException extends RuntimeException {

    public CustomInvalidParamException(String message) {
        super(message);
    }

    public CustomInvalidParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
