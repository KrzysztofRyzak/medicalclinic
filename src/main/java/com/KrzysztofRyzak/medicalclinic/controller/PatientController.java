package com.KrzysztofRyzak.medicalclinic.controller;

import com.KrzysztofRyzak.medicalclinic.model.Patient;
import com.KrzysztofRyzak.medicalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    //GET /patients
    @GetMapping
    public List<Patient> getPatients() {
        return service.getPatients();
    }

    @GetMapping("/{email}")
    public Patient getPatientByEmail(@PathVariable String email) {
        return service.getPatientByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Patient not found"
                ));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient addPatient(@RequestBody Patient patient) {
        return service.addPatient(patient);
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable String email) {
        service.deletePatient(email);
    }

    @PutMapping("/{email}")
    public Patient updatePatient(
            @PathVariable String email,
            @RequestBody Patient updatedPatient) {
        return service.updatePatient(email, updatedPatient);
    }
}
