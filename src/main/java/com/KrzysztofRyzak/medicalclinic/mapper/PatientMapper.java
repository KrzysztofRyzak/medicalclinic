package com.KrzysztofRyzak.medicalclinic.mapper;

import com.KrzysztofRyzak.medicalclinic.dto.PatientDto;
import com.KrzysztofRyzak.medicalclinic.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDto toDto(Patient patient);
}
