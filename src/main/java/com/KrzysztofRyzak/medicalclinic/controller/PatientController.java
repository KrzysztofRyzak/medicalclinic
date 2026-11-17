package com.KrzysztofRyzak.medicalclinic.controller;

import com.KrzysztofRyzak.medicalclinic.model.Patient;
import com.KrzysztofRyzak.medicalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // wskazuje ze dana klasa jest beanem springowym oraz kontrolerem restowym
                // tutaj bede mial definicje endpointow
@RequiredArgsConstructor
@RequestMapping("/patients") // kazdy endpoint w tym kontrolerze zaczynac sie bedzie od /patients

public class PatientController {

    private final PatientService service;

    //GET /patients
    @GetMapping
    List<Patient>  getPatients() {
        return service.getPatients();
    }

    @GetMapping("/{email}")
    public Patient getPatientByEmail(@PathVariable String email) {
        Patient patient = service.getPatientByEmail(email);

        if (patient == null) {
            throw new org.springframework.web.server.ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Patient not found"
            );
        }

        return patient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPatient(@RequestBody Patient patient) {
        service.addPatient(patient);
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable String email) {
        service.deletePatient(email);
    }

}
