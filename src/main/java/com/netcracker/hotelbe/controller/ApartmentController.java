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
    public ResponseEntity getAll() {
        return new ResponseEntity(apartmentService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/{apartmentClassId}")
    public ResponseEntity create(@RequestBody Apartment apartment, @PathVariable Long apartmentClassId) {
        return new ResponseEntity(apartmentService.save(apartment, apartmentClassId),
                HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        Apartment apartment = apartmentService.findById(id);
        if (apartment != null) {
            return new ResponseEntity(apartment, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{apartmentClassId}")
    public ResponseEntity update(@RequestBody Apartment apartment, @PathVariable Long apartmentClassId) {
        boolean update = apartmentService.update(apartment, apartmentClassId);

        if (update) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        boolean delete = apartmentService.deleteById(id);
        if (delete) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
