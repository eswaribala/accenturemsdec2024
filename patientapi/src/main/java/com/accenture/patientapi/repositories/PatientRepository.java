package com.accenture.patientapi.repositories;

import com.accenture.patientapi.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    @Query("select p from Patient p where p.contactNo=:contactNo")
    List<Patient> findPatientByContactNo(@Param("contactNo") long contactNo);
}
