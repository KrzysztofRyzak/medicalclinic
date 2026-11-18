package com.KrzysztofRyzak.medicalclinic.repository;

import com.KrzysztofRyzak.medicalclinic.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class PatientRepository {

    private final List<Patient> patients = new ArrayList<>();

    public List<Patient> findAll() {
        return new ArrayList<>(patients);
    }

    public Optional<Patient> findByEmail(String email) {
        return patients.stream()
                .filter(patient -> Objects.equals(patient.getEmail(), email))
                .findFirst();
    }

    public Patient save(Patient patient) {
        patients.add(patient);
        return patient;
    }

    public boolean deleteByEmail(String email) {
        return findByEmail(email)
                .map(patients::remove)
                .orElse(false);
    }

    public Optional<Patient> editPatientByEmail(String email, Patient updatedPatient) {
        return findByEmail(email)
                .map(patient -> {
                    patient.setFirstName(updatedPatient.getFirstName());
                    patient.setLastName(updatedPatient.getLastName());
                    patient.setPhoneNumber(updatedPatient.getPhoneNumber());
                    patient.setIdCardNo(updatedPatient.getIdCardNo());
                    patient.setBirthday(updatedPatient.getBirthday());
                    return patient;
                });
    }
}
