package com.docbook.specializations.service;

import com.docbook.specializations.entity.Specialization;
import com.docbook.specializations.payload.SpecializationDto;

public interface SpecializationService {
    SpecializationDto addDoctorSpecialization(SpecializationDto dto);

    String deleteDoctorSpecialization(long specializationId);

    SpecializationDto searchDoctorSpecialization(long specializationId);
}
