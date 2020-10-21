package com.taco.loco.exception;

public class MenuItemNotFoundException extends RuntimeException {

    public MenuItemNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public MenuItemNotFoundException(Throwable error) {
        super(error);
    }

    public MenuItemNotFoundException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
