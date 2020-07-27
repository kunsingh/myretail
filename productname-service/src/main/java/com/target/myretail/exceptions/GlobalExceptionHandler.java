package com.target.myretail.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNameNotFoundException.class)
    public ResponseEntity<?> productNameNotFoundException(final ProductNameNotFoundException ex, final WebRequest req) {
        return new ResponseEntity<>("Product name not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgumentException(final IllegalArgumentException ex, final WebRequest req) {
        return new ResponseEntity<>("Illegal Argument", HttpStatus.BAD_REQUEST);
    };

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exception(final Exception ex, final WebRequest req) {
        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
