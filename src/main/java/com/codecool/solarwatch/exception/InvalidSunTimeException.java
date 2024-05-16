package com.codecool.solarwatch.exception;

public class InvalidSunTimeException extends RuntimeException {
    public class InvalidCityException extends RuntimeException {

        public InvalidCityException(String message) {
            super(message);
        }
    }
}
