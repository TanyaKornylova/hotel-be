package com.netcracker.hotelbe.controller;


import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.service.ApartmentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("apartmentsClasses")
public class ApartmentClassController {

    @Autowired
    private ApartmentClassService apartmentClassService;

    @GetMapping
    public ResponseEntity<List<ApartmentClass>> getAll() {
        return new ResponseEntity<>(apartmentClassService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApartmentClass> getById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(apartmentClassService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApartmentClass> create(@RequestBody @Valid ApartmentClass apartmentClass) {
        return new ResponseEntity<>(apartmentClassService.save(apartmentClass),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApartmentClass> update(@RequestBody @Valid ApartmentClass apartmentClass, @PathVariable Long id) {
        return new ResponseEntity<>(apartmentClassService.update(apartmentClass, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") final Long id) {
        apartmentClassService.deleteById(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
