package com.accenture.patientapi.exceptions;

import com.accenture.patientapi.controllers.PatientController;
import com.accenture.patientapi.dtos.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
//@Slf4j
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(PatientMobileInValidException.class)
    public ResponseEntity<GenericResponse> handlePatientMobileInValidException(PatientMobileInValidException patientMobileInValidException){
        LOGGER.error(patientMobileInValidException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse(patientMobileInValidException.getMessage()));

    }
    @ExceptionHandler(PatientIdNotFoundException.class)
    public ResponseEntity<GenericResponse> handlePatientNotFoundException(PatientIdNotFoundException patientIdNotFoundException){
        LOGGER.error(patientIdNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse(patientIdNotFoundException.getMessage()));

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GenericResponse> handleRuntimeException(RuntimeException runtimeException){
        LOGGER.error(runtimeException.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GenericResponse(runtimeException.getMessage()));

    }
}
