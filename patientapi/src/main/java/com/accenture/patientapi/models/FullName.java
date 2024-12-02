package com.accenture.patientapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullName {

    @Column(name="First_Name",nullable = false,length = 150)
    private String firstName;
    @Column(name="Last_Name",nullable = false,length = 150)
    private String lastName;
}
