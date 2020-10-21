package com.taco.loco.exception;

public class InvalidOrderException extends RuntimeException {

    public InvalidOrderException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidOrderException(Throwable error) {
        super(error);
    }

    public InvalidOrderException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
