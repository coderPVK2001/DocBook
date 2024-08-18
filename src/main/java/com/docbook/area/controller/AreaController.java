package com.docbook.area.controller;

import com.docbook.area.payload.AreaDto;
import com.docbook.area.service.AreaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dockbook/area")
public class AreaController {

    private AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addarea(@RequestBody AreaDto areaDto){

        return new ResponseEntity<>(areaService.add(areaDto), HttpStatus.OK);
    }

    //update area
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateArea(@RequestBody AreaDto areaDto,@PathVariable Long id){

        return new ResponseEntity<>(areaService.update(id, areaDto), HttpStatus.OK);
    }

   @DeleteMapping("/delete")
    public ResponseEntity<?> deleteArea( @RequestParam String name){

       areaService.delete(name);
        return new ResponseEntity<>("deleted Successfully!!", HttpStatus.OK);
   }
}
