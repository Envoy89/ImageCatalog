package com.theruzil.image_catalog.exception;

public class AppException extends RuntimeException {
    public AppException(String message, Throwable err) {
        super(message, err);
    }

    public AppException(String message) {
        super(message);
    }
}
