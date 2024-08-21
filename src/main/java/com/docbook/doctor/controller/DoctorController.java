package com.docbook.doctor.controller;

import com.docbook.doctor.payload.Doctordto;
import com.docbook.doctor.service.DoctorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/v1/docbook/doctor")
@RestController
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addDoctor(@RequestPart("doctorDto") String doctorDtoJson, @RequestPart("file") MultipartFile file){

        ObjectMapper objectMapper = new ObjectMapper();
        Doctordto doctorDto;
        try {
            doctorDto = objectMapper.readValue(doctorDtoJson, Doctordto.class);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Invalid JSON format", HttpStatus.BAD_REQUEST);
        }

        Doctordto doctordto1 = doctorService.addDoctor(doctorDto, file);
        return new ResponseEntity<>(doctordto1, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<?> search(
            @RequestParam( required = true, value = "specialization") String specialization,
            @RequestParam( required = false, value = "area") String area,
            @RequestParam( required = false, value = "city") String city
    ){
        List<Doctordto> doctordto;
        if(area!=null){
            doctordto = doctorService.searchByAreaAndSpecialization(area, specialization);
        }
        else if(city!=null){
            doctordto = doctorService.searchByCityAndSpecialization(city, specialization);
        }
        else{
            return new ResponseEntity<>("enter params properly", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(doctordto, HttpStatus.OK);
    }
}
