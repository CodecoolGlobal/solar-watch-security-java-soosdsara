package com.codecool.solarwatch.controller;

import com.codecool.solarwatch.exception.InvalidCityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.format.DateTimeParseException;
@ControllerAdvice
public class SunTimesControllerAdvice {

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

}
