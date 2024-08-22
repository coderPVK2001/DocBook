package com.docbook.patient.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientDto {

    @NotNull
    @Size(min = 3 ,max = 10, message = "characters should be in range of 3-10 ")
    private String name;

    @Size(min = 10, max = 10, message = "mobile number should be 10 digits !!")
    private String mobile;

    @Email(message = "enter valid email-id !!")
    private String email;
}
