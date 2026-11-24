package com.KrzysztofRyzak.medicalclinic.service;

import com.KrzysztofRyzak.medicalclinic.ChangePasswordCommand;
import com.KrzysztofRyzak.medicalclinic.dto.PatientDto;
import com.KrzysztofRyzak.medicalclinic.exception.EmailAlreadyInUseException;
import com.KrzysztofRyzak.medicalclinic.exception.PatientNotFoundException;
import com.KrzysztofRyzak.medicalclinic.mapper.PatientMapper;
import com.KrzysztofRyzak.medicalclinic.model.Patient;
import com.KrzysztofRyzak.medicalclinic.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public List<PatientDto> getPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDto)
                .toList();
    }

    public PatientDto getPatientByEmail(String email) {
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(() -> new PatientNotFoundException(email));
        return patientMapper.toDto(patient);
    }

    public PatientDto addPatient(Patient patient) {
        if (patientRepository.findByEmail(patient.getEmail()).isPresent()) {
            throw new EmailAlreadyInUseException(patient.getEmail());
        }
        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.toDto(savedPatient);
    }

    public void deletePatient(String email) {
        if (!patientRepository.deleteByEmail(email)) {
            throw new PatientNotFoundException(email);
        }
    }

    public PatientDto updatePatient(String email, Patient updatedPatient) {
        Patient patient = patientRepository.editPatientByEmail(email, updatedPatient)
                .orElseThrow(() -> new PatientNotFoundException(email));
        return patientMapper.toDto(patient);
    }

    public void changePassword(String email, ChangePasswordCommand changePasswordCommand) {
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(() -> new PatientNotFoundException(email));
        patient.setPassword(changePasswordCommand.newPassword());
    }
}
