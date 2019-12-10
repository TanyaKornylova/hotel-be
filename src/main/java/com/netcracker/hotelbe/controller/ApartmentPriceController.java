package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.ApartmentPrice;
import com.netcracker.hotelbe.service.ApartmentPriceService;
import com.netcracker.hotelbe.utils.RuntimeExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apartmentPrices")
public class ApartmentPriceController {

    @Autowired
    private ApartmentPriceService apartmentPriceService;

    @GetMapping
    public ResponseEntity<List<ApartmentPrice>> getAll() {
        return new ResponseEntity<>(apartmentPriceService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApartmentPrice> getById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(apartmentPriceService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApartmentPrice> create(@RequestBody ApartmentPrice apartmentPrice) {
        try {
            return new ResponseEntity<>(apartmentPriceService.save(apartmentPrice), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return RuntimeExceptionHandler.handlePSQLException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApartmentPrice> update(@RequestBody ApartmentPrice apartmentPrice, @PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(apartmentPriceService.update(apartmentPrice, id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return RuntimeExceptionHandler.handlePSQLException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") final Long id) {
        apartmentPriceService.deleteById(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
