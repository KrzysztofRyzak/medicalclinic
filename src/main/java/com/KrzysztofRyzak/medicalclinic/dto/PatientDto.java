package com.KrzysztofRyzak.medicalclinic.dto;

public record PatientDto(
        String firstName,
        String lastname,
        String email,
        String phoneNumber
) {

}
