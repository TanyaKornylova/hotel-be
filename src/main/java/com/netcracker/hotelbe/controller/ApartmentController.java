package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.Apartment;
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
    public ResponseEntity createApartment(@RequestBody Apartment apartment, @PathVariable Long apartmentClassId) {
        return new ResponseEntity(apartmentService.createApartment(apartment, apartmentClassId),
                HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity getApartmentById(@PathVariable Long id) {
        Apartment apartment = apartmentService.readApartment(id);
        if (apartment != null) {
            return new ResponseEntity(apartment, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{apartmentClassId}")
    public ResponseEntity updateApartment(@RequestBody Apartment apartment, @PathVariable Long apartmentClassId) {
        boolean update = apartmentService.updateApartment(apartment, apartmentClassId);

        if (update) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteApartment(@PathVariable Long id) {
        boolean delete = apartmentService.deleteApartment(id);
        if (delete) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
