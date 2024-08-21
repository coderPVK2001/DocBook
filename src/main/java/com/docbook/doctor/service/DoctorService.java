package com.docbook.doctor.service;

import com.docbook.doctor.payload.Doctordto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DoctorService {


    Doctordto addDoctor(Doctordto doctordto, MultipartFile file);

    List<Doctordto> searchByAreaAndSpecialization(String area, String specialization);
    List<Doctordto> searchByCityAndSpecialization( String city, String specialization);
}
