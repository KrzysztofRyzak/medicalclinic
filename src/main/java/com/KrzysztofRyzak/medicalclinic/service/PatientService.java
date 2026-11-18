package com.KrzysztofRyzak.medicalclinic.service;

import com.KrzysztofRyzak.medicalclinic.exception.EmailAlreadyInUseException;
import com.KrzysztofRyzak.medicalclinic.exception.PatientNotFoundException;
import com.KrzysztofRyzak.medicalclinic.model.Patient;
import com.KrzysztofRyzak.medicalclinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    public Patient addPatient(Patient patient) {
        if (patientRepository.findByEmail(patient.getEmail()).isPresent()) {
            throw new EmailAlreadyInUseException(patient.getEmail());
        }
        return patientRepository.save(patient);
    }

    public void deletePatient(String email) {
        patientRepository.deleteByEmail(email);
    }

    public Patient updatePatient(String email, Patient updatedPatient) {
        return patientRepository.editPatientByEmail(email, updatedPatient)
                .orElseThrow(() -> new PatientNotFoundException(email));
    }
}
