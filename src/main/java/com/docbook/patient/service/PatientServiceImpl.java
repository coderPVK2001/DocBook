package com.docbook.patient.service;

import com.docbook.patient.entity.Patient;
import com.docbook.patient.payload.PatientDto;
import com.docbook.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService{

    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientDto addDetails(PatientDto patientDto) {

        Patient patient=new Patient();
        patient.setEmail(patientDto.getEmail());
        patient.setName(patientDto.getName());
        patient.setMobile(patientDto.getMobile());
        Patient savedPatient = patientRepository.save(patient);

        PatientDto patientDto1=new PatientDto();
        patientDto1.setEmail(savedPatient.getEmail());
        patientDto1.setMobile(savedPatient.getMobile());
        patientDto1.setName(savedPatient.getName());

        return patientDto1;
    }
}
