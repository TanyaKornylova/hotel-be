package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apartment")
public class ApartmentController {
    @Autowired
    ApartmentService apartmentService;

    @GetMapping("/all")
    public ResponseEntity getAllApartments() {
        return new ResponseEntity(apartmentService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/{apartmentClassId}")
    public ResponseEntity addApartment(@RequestBody Apartment apartment, @PathVariable Long apartmentClassId) {
       boolean result = apartmentService.addApartment(apartment, apartmentClassId);
       if(result){
           return new ResponseEntity(HttpStatus.CREATED);
       } else {
           return new ResponseEntity(HttpStatus.BAD_REQUEST);
       }
    }
}
