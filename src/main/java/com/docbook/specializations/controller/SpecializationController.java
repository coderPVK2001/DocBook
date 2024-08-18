package com.docbook.specializations.controller;

import com.docbook.specializations.entity.Specialization;
import com.docbook.specializations.payload.SpecializationDto;
import com.docbook.specializations.service.SpecializationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dockbook/specialization")
public class SpecializationController {
   private SpecializationService specializationService;

    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDoctorSpecialization(@RequestBody SpecializationDto dto) {
        return new ResponseEntity<>(specializationService.addDoctorSpecialization(dto), HttpStatus.CREATED);
    };

    @DeleteMapping("/delete/{specializationId}")
    public ResponseEntity<?> deleteDoctorSpecialization(@PathVariable long SpecializationId ) {
        return new ResponseEntity<>(specializationService.deleteDoctorSpecialization(SpecializationId), HttpStatus.OK);
    };

    @GetMapping("/search")
    public ResponseEntity<?> searchDoctorSpecialization(@RequestParam long specializationId ) {

        return new ResponseEntity<>(specializationService.searchDoctorSpecialization(specializationId), HttpStatus.OK);
    };


}
