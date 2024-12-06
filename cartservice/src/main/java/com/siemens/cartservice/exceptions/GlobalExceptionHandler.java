package com.siemens.cartservice.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.siemens.cartservice.dtos.GenericMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<GenericMessage> handleJsonProcessingException(JsonProcessingException ex){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericMessage(ex.getMessage()));
    }
}
