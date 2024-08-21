package com.docbook.doctor.service;

import com.docbook.area.entity.Area;
import com.docbook.area.exception.AreaNotFoundException;
import com.docbook.area.repository.AreaRepository;
import com.docbook.city.entity.City;
import com.docbook.city.exception.CityNotFoundException;
import com.docbook.city.exception.NoCityFoundException;
import com.docbook.city.repository.CityRepository;
import com.docbook.clinic.entity.Clinic;
import com.docbook.clinic.exception.ClinicNotFoundException;
import com.docbook.clinic.repository.ClinicRepository;
import com.docbook.doctor.entity.Doctor;
import com.docbook.doctor.payload.Doctordto;
import com.docbook.doctor.repository.DoctorRepository;
import com.docbook.specializations.entity.Specialization;
import com.docbook.specializations.exception.SpecializationNotFound;
import com.docbook.specializations.repository.SpecializationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService{


    private S3Service s3Service;
    private AreaRepository areaRepository;
    private CityRepository cityRepository;
    private SpecializationRepository specializationRepository;
    private ClinicRepository clinicRepository;
    private DoctorRepository doctorRepository;

    public DoctorServiceImpl(S3Service s3Service, AreaRepository areaRepository, CityRepository cityRepository, SpecializationRepository specializationRepository, ClinicRepository clinicRepository, DoctorRepository doctorRepository) {
        this.s3Service = s3Service;
        this.areaRepository = areaRepository;
        this.cityRepository = cityRepository;
        this.specializationRepository = specializationRepository;
        this.clinicRepository = clinicRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctordto addDoctor(Doctordto doctordto, MultipartFile file) {

        Doctor doctor = dtoToEntity(doctordto, file);

        Doctordto doctordto1 = entityToDto(doctor);

        return doctordto1;
    }

    Doctor dtoToEntity(Doctordto doctorDto, MultipartFile multipartFile){
        Doctor doctor=new Doctor();
        doctor.setDoctorName(doctorDto.getName());
        doctor.setMobile(doctorDto.getMobile());
        doctor.setEmail(doctorDto.getEmail());

        String url=null;
        try {
            url = s3Service.uploadFile(multipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        doctor.setImageUrl(url);

        Area area = areaRepository.findByAreaname(doctorDto.getAreaname()).orElseThrow(() -> new AreaNotFoundException("area not found!!"));
        doctor.setArea(area);

        Clinic clinic = clinicRepository.findByClinicName(doctorDto.getClinic()).orElseThrow(() -> new ClinicNotFoundException("clinic not found!!"));
        doctor.setClinic(clinic);

        Specialization specialization = specializationRepository.findByExpertise(doctorDto.getSpecialization()).orElseThrow(() -> new SpecializationNotFound("specialization not found!!"));
        doctor.setSpecialization(specialization);

        City city = cityRepository.findByCityname(doctorDto.getCity()).orElseThrow(() -> new NoCityFoundException("city not found!!"));
        doctor.setCity(city);

        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctordto> searchByAreaAndSpecialization(String area, String specialization) {
        Area area1 = areaRepository.findByAreaname(area).orElseThrow(() -> new AreaNotFoundException("area not found"));
        Specialization specialization1 = specializationRepository.findByExpertise(specialization).orElseThrow(() -> new SpecializationNotFound("specialization not found!!"));
        List<Doctor> listData = doctorRepository.findByAreaSpecialization(area1, specialization1);
        List<Doctordto> result = listData.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Doctordto> searchByCityAndSpecialization(String city, String specialization) {

        City city1 = cityRepository.findByCityname(city).orElseThrow(() -> new NoCityFoundException("city not found!!"));
        Specialization specialization1 = specializationRepository.findByExpertise(specialization).orElseThrow(() -> new SpecializationNotFound("specialization not found!!"));
        List<Doctor> result=doctorRepository.findByCitySpecialization(city1, specialization1);
        List<Doctordto> collect = result.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
        return collect;
    }

    Doctordto entityToDto(Doctor doctor){

        Doctordto dto=new Doctordto();
        dto.setName(doctor.getDoctorName());
        dto.setMobile(doctor.getMobile());
        dto.setEmail(doctor.getEmail());
        dto.setCity(doctor.getCity().getCityname());
        dto.setClinic(doctor.getClinic().getClinicName());
        dto.setSpecialization(doctor.getSpecialization().getExpertise());
        dto.setClinic(doctor.getClinic().getClinicName());
        dto.setAreaname(doctor.getArea().getAreaname());
        dto.setUrl(doctor.getImageUrl());

        return dto;
    }
}
