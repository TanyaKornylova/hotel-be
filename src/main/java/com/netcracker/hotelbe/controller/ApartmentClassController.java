package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.service.ApartmentClassService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("apartment-class")
public class ApartmentClassController {
    private static Logger logger = LogManager.getLogger(ApartmentClassController.class);

    @Autowired
    private ApartmentClassService apartmentClassService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        logger.info("Request for get all apartment classes");

        return new ResponseEntity(apartmentClassService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        logger.info("Request for get apartment class by Id: " + id);

        return new ResponseEntity(apartmentClassService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid ApartmentClass apartmentClass) {
        logger.info("Request for create apartment class");

        return new ResponseEntity(apartmentClassService.save(apartmentClass),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid ApartmentClass apartmentClass) {
        logger.info("Request for update apartment class by id: " + apartmentClass.getId());

        return new ResponseEntity(apartmentClassService.update(apartmentClass), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        logger.info("Request for delete apartment class by id: " + id);

        apartmentClassService.deleteById(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
