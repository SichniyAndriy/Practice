package org.example.controller;

import org.example.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CountryControllerAdvice {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetails> exceptionRuntimeException(RuntimeException e) {
        ErrorDetails error = new ErrorDetails(e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header("Status", HttpStatus.SERVICE_UNAVAILABLE.name())
                .body(error);
    }
}
