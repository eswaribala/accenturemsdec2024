package com.accenture.patientapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Acc_Patient")
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Patient_Id")
    private long patientId;
    @Embedded
    private FullName fullName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="DOB")
    private LocalDate dob;
    @Enumerated(EnumType.STRING)
    @Column(name="Gender")
    private Gender gender;
    @Enumerated(EnumType.STRING)
    @Column(name="Patient_Type")

    private PatientType patientType;
    @Column(name="Contact_No")
    private long contactNo;


}
