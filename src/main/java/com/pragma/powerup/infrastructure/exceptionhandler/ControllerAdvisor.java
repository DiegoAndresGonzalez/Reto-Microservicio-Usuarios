package com.pragma.powerup.infrastructure.exceptionhandler;

import com.pragma.powerup.domain.exception.DataNotFoundException;
import com.pragma.powerup.domain.exception.InvalidInputException;
import com.pragma.powerup.domain.exception.DuplicateEmailException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException ignoredNoDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NO_DATA_FOUND.getMessage()));
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Map<String,String>> handleInputException(
            InvalidInputException invalidInputException){
        Map<String,String> exResponse = new HashMap<>();
        exResponse.put(MESSAGE, invalidInputException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exResponse);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String,String>> handleEmailFoundEx(
            DuplicateEmailException foundEx){
        Map<String,String> exResponse = new HashMap<>();
        exResponse.put(MESSAGE,foundEx.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exResponse);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleDataNotFoundEx(
            DataNotFoundException notFoundEx){
        Map<String,String> exResponse = new HashMap<>();
        exResponse.put(MESSAGE,notFoundEx.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exResponse);
    }

}