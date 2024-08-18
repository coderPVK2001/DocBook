package com.docbook.clinic.service;

import com.docbook.clinic.payload.ClinicDto;

public interface ClinicService {
    ClinicDto addClinic(ClinicDto dto);

    String deleteClinic(long clinicId);

    ClinicDto searchClinic(String clinicName);
}
