package com.KrzysztofRyzak.medicalclinic.service;

import com.KrzysztofRyzak.medicalclinic.dto.PatientDto;
import com.KrzysztofRyzak.medicalclinic.mapper.PatientMapper;
import com.KrzysztofRyzak.medicalclinic.model.Patient;
import com.KrzysztofRyzak.medicalclinic.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class PatientServiceTest {

    PatientService patientService;
    PatientRepository patientRepository;
    PatientMapper patientMapper;

    @BeforeEach
    void setup() {
        this.patientRepository = Mockito.mock(PatientRepository.class);
        this.patientMapper = Mappers.getMapper(PatientMapper.class);
        this.patientService = new PatientService(patientRepository, patientMapper);
    }

    @Test
    void getPatients_DataCorrect_PatientsReturned() {
        // given
        List<Patient> patients = List.of(
                new Patient(
                        "jan.kowalski@example.com",
                        "Password123!",
                        "ABC123456",
                        "Jan",
                        "Kowalski",
                        "123456789",
                        LocalDate.of(1995, 5, 15)
                ),
                new Patient(
                        "anna.nowak@example.com",
                        "Password456!",
                        "XYZ654321",
                        "Anna",
                        "Nowak",
                        "987654321",
                        LocalDate.of(1998, 8, 22)
                )
        );
        when(patientRepository.findAll()).thenReturn(patients);
        // when
        List<PatientDto> result = patientService.getPatients();
        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, result.size()),
                () -> Assertions.assertEquals("jan.kowalski@example.com", result.get(0).email()),
                () -> Assertions.assertEquals("Jan", result.get(0).firstName()),
                () -> Assertions.assertEquals("Kowalski", result.get(0).lastName()),
                () -> Assertions.assertEquals("123456789", result.get(0).phoneNumber()),
                () -> Assertions.assertEquals(LocalDate.of(1995, 5, 15), result.get(0).birthday()),
                () -> Assertions.assertEquals("anna.nowak@example.com", result.get(1).email()),
                () -> Assertions.assertEquals("Anna", result.get(1).firstName()),
                () -> Assertions.assertEquals("Nowak", result.get(1).lastName()),
                () -> Assertions.assertEquals("987654321", result.get(1).phoneNumber()),
                () -> Assertions.assertEquals(LocalDate.of(1998, 8, 22), result.get(1).birthday())
        );
    }

    @Test
    void getPatientByEmail_PatientExists_PatientReturned() {
        // given
        Patient patient = new Patient(
                "jan.kowalski@example.com",
                "Password123!",
                "ABC123456",
                "Jan",
                "Kowalski",
                "123456789",
                LocalDate.of(1995, 5, 15)
        );
        when(patientRepository.findByEmail(patient.getEmail())).thenReturn(Optional.of(patient));
        // when
        PatientDto result = patientService.getPatientByEmail(patient.getEmail());
        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals("jan.kowalski@example.com", result.email()),
                () -> Assertions.assertEquals("Jan", result.firstName()),
                () -> Assertions.assertEquals("Kowalski", result.lastName()),
                () -> Assertions.assertEquals("123456789", result.phoneNumber()),
                () -> Assertions.assertEquals(LocalDate.of(1995, 5, 15), result.birthday())
        );
    }

    @Test
    void addPatient_PatientDoesNotExist_PatientReturned() {
        // given
        Patient patient = new Patient(
                "jan.kowalski@example.com",
                "Password123!",
                "ABC123456",
                "Jan",
                "Kowalski",
                "123456789",
                LocalDate.of(1995, 5, 15)
        );
        when(patientRepository.findByEmail(patient.getEmail())).thenReturn(Optional.empty());
        when(patientRepository.save(patient)).thenReturn(patient);
        // when
        PatientDto result = patientService.addPatient(patient);
        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals("jan.kowalski@example.com", result.email()),
                () -> Assertions.assertEquals("Jan", result.firstName()),
                () -> Assertions.assertEquals("Kowalski", result.lastName()),
                () -> Assertions.assertEquals("123456789", result.phoneNumber()),
                () -> Assertions.assertEquals(LocalDate.of(1995, 5, 15), result.birthday())
        );
    }

    @Test
    void updatePatient_PatientExists_UpdatedPatientReturned() {
        // given
        String email = "jan.kowalski@example.com";
        Patient updatedPatient = new Patient(
                null,
                null,
                "ABC123456",
                "Jan",
                "Kowalski",
                "123456789",
                LocalDate.of(1995, 5, 15)
        );

        Patient savedPatient = new Patient(
                "jan.kowalski@example.com",
                "Password123!",
                "ABC123456",
                "Jan",
                "Kowalski",
                "123456789",
                LocalDate.of(1995, 5, 15)
        );

        when(patientRepository.editPatientByEmail(email, updatedPatient)).thenReturn(Optional.of(savedPatient));
        // when
        PatientDto result = patientService.updatePatient(email, updatedPatient);
        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals("jan.kowalski@example.com", result.email()),
                () -> Assertions.assertEquals("Jan", result.firstName()),
                () -> Assertions.assertEquals("Kowalski", result.lastName()),
                () -> Assertions.assertEquals("123456789", result.phoneNumber()),
                () -> Assertions.assertEquals(LocalDate.of(1995, 5, 15), result.birthday())
        );
    }
}
