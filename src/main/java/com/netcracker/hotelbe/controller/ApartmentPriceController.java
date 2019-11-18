package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.ApartmentPrice;
import com.netcracker.hotelbe.service.ApartmentPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apartment-price")
public class ApartmentPriceController {

    @Autowired
    ApartmentPriceService apartmentPriceService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        return new ResponseEntity(apartmentPriceService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/{apartmentId}")
    public ResponseEntity create(@RequestBody ApartmentPrice apartmentPrice,
                                 @PathVariable Long apartmentId) {
        return new ResponseEntity(apartmentPriceService.save(apartmentPrice, apartmentId),
                HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        ApartmentPrice apartmentPrice = apartmentPriceService.findById(id);
        if (apartmentPrice != null) {
            return new ResponseEntity(apartmentPrice, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{apartmentId}")
    public ResponseEntity update(@RequestBody ApartmentPrice apartmentPrice,
                                 @PathVariable Long apartmentId) {
        boolean update = apartmentPriceService.update(apartmentPrice, apartmentId);

        if (update) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        boolean delete = apartmentPriceService.deleteById(id);
        if (delete) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
