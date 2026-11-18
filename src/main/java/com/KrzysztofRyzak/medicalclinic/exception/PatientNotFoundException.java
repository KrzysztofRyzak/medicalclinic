package com.KrzysztofRyzak.medicalclinic.exception;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(String email) {
        super("Patient not found with email: " + email);
    }
}
