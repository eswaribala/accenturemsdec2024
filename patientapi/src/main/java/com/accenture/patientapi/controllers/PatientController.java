package com.accenture.patientapi.controllers;

import com.accenture.patientapi.dtos.GenericResponse;
import com.accenture.patientapi.dtos.PatientRequest;
import com.accenture.patientapi.models.FullName;
import com.accenture.patientapi.models.Patient;
import com.accenture.patientapi.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @PostMapping("/v1.0")
    ResponseEntity<GenericResponse> addPatient(@Valid @RequestBody PatientRequest patientRequest){

        Patient patient=Patient.builder()
                .dob(patientRequest.getDob())
                .patientType(patientRequest.getPatientType())
                .contactNo(patientRequest.getContactNo())
                .fullName(FullName.builder()
                        .firstName(patientRequest.getFullNameRequest().getFirstName())
                        .lastName(patientRequest.getFullNameRequest().getLastName())
                        .build())
                .gender(patientRequest.getGender())
                .build();

       Patient patientObj= patientService.addPatient(patient);


         return  ResponseEntity.status(HttpStatus.CREATED)
                   .body(new GenericResponse(patientObj));



    }



}
