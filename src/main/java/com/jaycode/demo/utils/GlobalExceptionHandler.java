package com.jaycode.demo.utils;

import com.jaycode.demo.models.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseModel<Object>> handleGlobalException(Exception ex, WebRequest request) {
        ResponseModel<Object> response = new ResponseModel<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                false,
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseModel<Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ResponseModel<Object> response = new ResponseModel<>(
                HttpStatus.NOT_FOUND.value(),
                false,
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseModel<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ResponseModel<Object> response = new ResponseModel<>(
                HttpStatus.BAD_REQUEST.value(),
                false,
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
