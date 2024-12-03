package com.accenture.patientapi.services;

import com.accenture.patientapi.models.Patient;

import java.util.List;

public interface PatientService {

    Patient addPatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(long patientId);
    List<Patient> getPatientByContactNo(long contactNo);

    Patient updateContactNo(long patientId, long newContactNo);

    boolean deletePatientById(long patientId);
}
