package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("apartments")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping
    public ResponseEntity<List<Apartment>> getAll() {
        return new ResponseEntity<>(apartmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apartment> getById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(apartmentService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Apartment> create(@RequestBody @Valid Apartment apartment) {
        return new ResponseEntity<>(apartmentService.save(apartment), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apartment> update(@RequestBody @Valid Apartment apartment, @PathVariable("id") Long id) {
        return new ResponseEntity<>(apartmentService.update(apartment, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") final Long id) {
        apartmentService.deleteById(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
