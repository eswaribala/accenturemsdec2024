package com.accenture.patientapi.exceptions;

import com.accenture.patientapi.dtos.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(PatientMobileInValidException.class)
    public ResponseEntity<GenericResponse> handlePatientMobileInValidException(PatientMobileInValidException patientMobileInValidException){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse(patientMobileInValidException.getMessage()));

    }
    @ExceptionHandler(PatientIdNotFoundException.class)
    public ResponseEntity<GenericResponse> handlePatientNotFoundException(PatientIdNotFoundException patientIdNotFoundException){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse(patientIdNotFoundException.getMessage()));

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GenericResponse> handleRuntimeException(RuntimeException runtimeException){

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GenericResponse(runtimeException.getMessage()));

    }
}
