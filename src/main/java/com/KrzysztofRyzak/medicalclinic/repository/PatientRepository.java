package com.KrzysztofRyzak.medicalclinic.repository;

import com.KrzysztofRyzak.medicalclinic.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientRepository {

    private final List<Patient> patients = new ArrayList<>();

    public List<Patient> findAll() {
        return new ArrayList<>(patients); // zwracam kopie
    }

    public Patient findByEmail(String email) {
        for (Patient patient : patients) {
            if (patient.getEmail().equals(email)) {
                return patient;
            }
        }
        return null;
    }

    public void save(Patient patient) {
        patients.add(patient);
    }

    public boolean deleteByEmail(String email) {
        return patients.removeIf(
                patient -> email.equals(patient.getEmail())
        );
    }
}
