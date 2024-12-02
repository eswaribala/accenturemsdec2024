package com.accenture.patientapi.repositories;

import com.accenture.patientapi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
