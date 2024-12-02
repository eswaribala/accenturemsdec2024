package com.accenture.patientapi.services;

import com.accenture.patientapi.models.Patient;

import java.util.List;

public interface PatientService {

    Patient addPatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(long patientId);
    Patient getPatientByContactNo(long contactNo);

    boolean deletePatientById(long patientId);
}
