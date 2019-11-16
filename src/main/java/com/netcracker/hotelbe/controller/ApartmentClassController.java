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

    @GetMapping("/{id}")
    public ResponseEntity getApartmentClassById(@PathVariable Long id) {
        ApartmentClass apartmentClass = apartmentClassService.readApartmentClass(id);
        if (apartmentClass != null) {
            return new ResponseEntity(apartmentClass, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity createApartmentClass(@RequestBody ApartmentClass apartmentClass) {
        return new ResponseEntity(apartmentClassService.createApartmentClass(apartmentClass),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateApartmentClass(@RequestBody ApartmentClass apartmentClass) {
        boolean update = apartmentClassService.updateApartmentClass(apartmentClass);

        if (update) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteApartmentClass(@PathVariable Long id) {
        boolean delete = apartmentClassService.deleteApartmentClass(id);
        if (delete) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
