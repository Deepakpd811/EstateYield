package com.estateyield.exception;

public class PropertyException extends RuntimeException {

    private final String code;

    public PropertyException(String message, String code) {
        super(message);
        this.code = code;
    }

    public PropertyException(String message, String code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}