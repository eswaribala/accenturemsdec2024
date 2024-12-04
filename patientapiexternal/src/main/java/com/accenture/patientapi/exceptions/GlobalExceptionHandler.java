package com.accenture.patientapi.exceptions;

import com.accenture.patientapi.dtos.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(PatientMobileInValidException.class)
    public ResponseEntity<GenericResponse> handlePatientMobileInValidException(PatientMobileInValidException patientMobileInValidException){
        log.error(patientMobileInValidException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse(patientMobileInValidException.getMessage()));

    }
    @ExceptionHandler(PatientIdNotFoundException.class)
    public ResponseEntity<GenericResponse> handlePatientNotFoundException(PatientIdNotFoundException patientIdNotFoundException){
        log.error(patientIdNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse(patientIdNotFoundException.getMessage()));

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GenericResponse> handleRuntimeException(RuntimeException runtimeException){
        log.error(runtimeException.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GenericResponse(runtimeException.getMessage()));

    }
}
