package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.service.ApartmentService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("apartment")
public class ApartmentController {
    private static Logger logger = LogManager.getLogger(ApartmentController.class);
    private final static String APARTMENT_BY_ID_NOT_FOUND = "Apartment by id: %d not found!";

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        logger.info("Request for get all apartments");

        return new ResponseEntity(apartmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        logger.info("Request for get apartment by id: " + id);

        return new ResponseEntity(apartmentService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/{apartmentClassId}")
    public ResponseEntity create(@RequestBody @Valid Apartment apartment, @PathVariable("apartmentClassId") Long apartmentClassId) {
        logger.info("Request for create apartment with apartmentClassId: " + apartmentClassId);

        return new ResponseEntity(apartmentService.save(apartment, apartmentClassId), HttpStatus.CREATED);
    }


    @PutMapping("/{apartmentClassId}")
    public ResponseEntity update(@RequestBody @Valid Apartment apartment, @PathVariable("apartmentClassId") Long apartmentClassId) {
        logger.info("Request for update apartment by id: " + apartment.getId() + " and apartmentClassId: " + apartmentClassId);

        return new ResponseEntity(apartmentService.update(apartment, apartmentClassId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id) {
        logger.info("Request for delete apartment by id: " + id);

        apartmentService.deleteById(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
