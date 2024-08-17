package com.docbook.specializations.service;

import com.docbook.specializations.entity.Specialization;
import com.docbook.specializations.exception.SpecializationAlreadyPresent;
import com.docbook.specializations.exception.SpecializationNotFound;
import com.docbook.specializations.payload.SpecializationDto;
import com.docbook.specializations.repository.SpecializationRepository;
import org.springframework.stereotype.Service;

@Service
public class SpecializationServiceImpl implements SpecializationService{
    private SpecializationRepository specializationRepository;

    public SpecializationServiceImpl(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Override
    public SpecializationDto addDoctorSpecialization(SpecializationDto dto) {
           if(specializationRepository.findByExpertise(dto.getExpertise()).isPresent()){
               throw new SpecializationAlreadyPresent("Specialization is already present!!");
           }
         Specialization specialization = new Specialization();
         specialization.setExpertise("Cardiologist");
         return specializationEntityToDto(specializationRepository.save(specialization));

    }

    @Override
    public String deleteDoctorSpecialization(long specializationId) {
         Specialization specialization = specializationRepository.findById(specializationId).orElseThrow(
                () -> new SpecializationNotFound("Specialization is not found for id " + specializationId)
        );
         specializationRepository.deleteById(specializationId);
        return "Record Deleted Successfully";
    }

    @Override
    public SpecializationDto searchDoctorSpecialization(long specializationId) {
        Specialization specialization = specializationRepository.findById(specializationId).orElseThrow(
                () -> new SpecializationNotFound("Specialization is not found for id " + specializationId)
        );
        return specializationEntityToDto(specialization);
    }

    private SpecializationDto specializationEntityToDto(Specialization savedSpcialization) {
       SpecializationDto specializationDto = new SpecializationDto();
       specializationDto.setExpertise(savedSpcialization.getExpertise());
       return specializationDto;
    }
}
