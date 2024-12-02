package com.accenture.patientapi.exceptions;

public class PatientIdNotFoundException extends RuntimeException{
    public PatientIdNotFoundException(String message) {
        super(message);
    }
}
