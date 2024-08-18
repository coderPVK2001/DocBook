package com.docbook.city.controller;

import com.docbook.city.payload.CityDto;
import com.docbook.city.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/docbook/city")
public class CityController {

    private CityService cityService;
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addcity(@RequestBody CityDto dto){

        return new ResponseEntity<>(cityService.add(dto), HttpStatus.CREATED);

    }

    //update city
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCity(@PathVariable Long id, @RequestBody CityDto dto) {

        return new ResponseEntity<>(cityService.update(id,dto), HttpStatus.OK);
    }
}
