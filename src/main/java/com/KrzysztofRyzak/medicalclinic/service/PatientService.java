package com.KrzysztofRyzak.medicalclinic.service;

import com.KrzysztofRyzak.medicalclinic.model.Patient;
import com.KrzysztofRyzak.medicalclinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    public void addPatient(Patient patient) {
        if (patientRepository.findByEmail(patient.getEmail()) != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Patient with this email already exists"
            );
        }

        patientRepository.save(patient);
    }

    public void deletePatient(String email) {
        boolean deleted = patientRepository.deleteByEmail(email);

        if (!deleted) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Patient not found"
            );
        }
    }
}
