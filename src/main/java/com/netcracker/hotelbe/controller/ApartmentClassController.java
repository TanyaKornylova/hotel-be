package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.service.ApartmentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apartment-class")
public class ApartmentClassController {

    @Autowired
    ApartmentClassService apartmentClassService;

    @GetMapping("/all")
    public ResponseEntity getAllApartmentClass() {
        return new ResponseEntity(apartmentClassService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addApartmentClass(@RequestBody ApartmentClass apartmentClass) {
        apartmentClassService.save(apartmentClass);
        return new ResponseEntity(apartmentClass, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getApartmentClassById(@PathVariable Long id) {
        return new ResponseEntity(apartmentClassService.getOne(id), HttpStatus.OK);
    }

}
