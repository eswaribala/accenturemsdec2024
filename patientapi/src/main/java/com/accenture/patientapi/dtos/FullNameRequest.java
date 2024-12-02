package com.accenture.patientapi.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullNameRequest {

    @NotNull(message = "First Name cannot be null")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First Name must contain only alphabets")

    private String firstName;
    @NotNull(message = "First Name cannot be null")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First Name must contain only alphabets")
    private String lastName;
}
