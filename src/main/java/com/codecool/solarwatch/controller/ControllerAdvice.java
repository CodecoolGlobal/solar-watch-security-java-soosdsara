package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.exception.InvalidCityException;
import jakarta.servlet.ServletException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ResponseBody
    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String DateTimeParseExceptionHandler() {
        return "Invalid date";
    }

    @ResponseBody
    @ExceptionHandler(InvalidCityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String InvalidCityExceptionHandler(InvalidCityException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String RuntimeExceptionHandler(RuntimeException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String DataIntegrityViolationExceptionHandler() {
        return "This username already exists. Please choose another one.";
    }

    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String UsernameNotFoundExceptionHandler(UsernameNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String NoSuchElementExceptionHandler(NoSuchElementException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ServletException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String ServletExceptionHandler(ServletException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String IOExceptionHandler(IOException ex) {
        return ex.getMessage();
    }

}
