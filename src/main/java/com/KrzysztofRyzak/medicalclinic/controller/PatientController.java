package com.KrzysztofRyzak.medicalclinic.controller;

import com.KrzysztofRyzak.medicalclinic.ChangePasswordCommand;
import com.KrzysztofRyzak.medicalclinic.dto.PatientDto;
import com.KrzysztofRyzak.medicalclinic.model.Patient;
import com.KrzysztofRyzak.medicalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;

    //GET /patients
    @GetMapping
    public List<PatientDto> getPatients() {
        return service.getPatients();
    }

    @GetMapping("/{email}")
    public PatientDto getPatientByEmail(@PathVariable String email) {
        return service.getPatientByEmail(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto addPatient(@RequestBody Patient patient) {
        return service.addPatient(patient);
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable String email) {
        service.deletePatient(email);
    }

    @PutMapping("/{email}")
    public PatientDto updatePatient(
            @PathVariable String email,
            @RequestBody Patient updatedPatient) {
        return service.updatePatient(email, updatedPatient);
    }

    @PatchMapping("/{email}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(
            @PathVariable String email,
            @RequestBody ChangePasswordCommand changePasswordCommand) {
        service.changePassword(email, changePasswordCommand);
    }
}

