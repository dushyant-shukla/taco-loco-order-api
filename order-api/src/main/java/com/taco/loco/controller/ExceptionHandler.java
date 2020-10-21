package com.taco.loco.controller;

import com.taco.loco.domain.ApiErrorModel;
import com.taco.loco.exception.InvalidOrderException;
import com.taco.loco.exception.MenuItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handlers
 */
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<ApiErrorModel> HandleInvalidOrderException(InvalidOrderException exception) {
        List<String> errors = new ArrayList<>();
        errors.add(exception.getMessage());
        return new ResponseEntity<ApiErrorModel>(new ApiErrorModel(errors), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorModel> HandleMethodArgumentsNotValid(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(result -> result.getField() + " : " + result.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<ApiErrorModel>(new ApiErrorModel(errors), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MenuItemNotFoundException.class)
    public ResponseEntity<ApiErrorModel> HandleInternalServerError(MenuItemNotFoundException exception) {
        List<String> errors = new ArrayList<>();
        errors.add(exception.getMessage());
        return new ResponseEntity<ApiErrorModel>(new ApiErrorModel(errors), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorModel> HandleInternalServerError(Exception exception) {
        List<String> errors = new ArrayList<>();
        errors.add("Something bad has happened, but it is not your fault. We are looking into it. Please try again after some time.");
        return new ResponseEntity<ApiErrorModel>(new ApiErrorModel(errors), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
