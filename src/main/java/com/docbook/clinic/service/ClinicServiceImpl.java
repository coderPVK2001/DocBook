package com.docbook.clinic.service;

import com.docbook.area.repository.AreaRepository;
import com.docbook.city.repository.CityRepository;
import com.docbook.clinic.entity.Clinic;
import com.docbook.clinic.exception.ClinicIsAlreadyExistsException;
import com.docbook.clinic.exception.ClinicNotFoundException;
import com.docbook.clinic.payload.ClinicDto;
import com.docbook.clinic.repository.ClinicRepository;
import org.springframework.stereotype.Service;

@Service
public class ClinicServiceImpl implements ClinicService {

    private ClinicRepository clinicRepository;
    private AreaRepository areaRepository;
    private CityRepository cityRepository;

    public ClinicServiceImpl(ClinicRepository clinicRepository, AreaRepository areaRepository, CityRepository cityRepository) {
        this.clinicRepository = clinicRepository;
        this.areaRepository = areaRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public ClinicDto addClinic(ClinicDto dto) {
       if(clinicRepository.findByClinicName(dto.getClinicName()).isPresent()){
          throw new ClinicIsAlreadyExistsException("Clinic already exists");
        }
         Clinic clinic = new Clinic();
         clinic.setClinicName(dto.getClinicName());
         clinic.setArea(areaRepository.findById(dto.getAreaId()).get());
         clinic.setCity(cityRepository.findById(dto.getCityId()).get());
         return clinicDtoToEntity(clinicRepository.save(clinic));
    }

    @Override
    public String deleteClinic(long clinicId) {
       if(clinicRepository.findById(clinicId).isEmpty()){
           throw new ClinicNotFoundException("Clinic is not found for id " + clinicId);

       }
       clinicRepository.deleteById(clinicId);
       return "Record deleted successfully";

    }

    @Override
    public ClinicDto searchClinic(String clinicName) {
         Clinic clinic = clinicRepository.findByClinicName(clinicName).orElseThrow(
                () -> new ClinicNotFoundException("Clinic not found with name " + clinicName)
        );
        return clinicDtoToEntity(clinic);
    }

    public  ClinicDto clinicDtoToEntity(Clinic clinic){
         ClinicDto dto = new ClinicDto();
         dto.setId(clinic.getId());
         dto.setAreaName(clinic.getArea().getAreaname());
         dto.setCityName(clinic.getCity().getCityname());
         dto.setClinicName(clinic.getClinicName());
         return dto;
    }
}
