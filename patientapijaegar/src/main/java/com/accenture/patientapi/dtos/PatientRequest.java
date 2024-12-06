package com.accenture.patientapi.dtos;

import com.accenture.patientapi.models.FullName;
import com.accenture.patientapi.models.Gender;
import com.accenture.patientapi.models.PatientType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {



    private FullNameRequest fullNameRequest;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dob;

    private Gender gender;


    private PatientType patientType;

    private long contactNo;

}
