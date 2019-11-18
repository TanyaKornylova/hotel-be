package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

@RestController
@RequestMapping("apartment")
public class ApartmentController {
    private final static Logger logger = Logger.getLogger(ApartmentController.class);
    private final static String APARTMENT_BY_ID_NOT_FOUND = "Apartment by id: %d not found!";

    @Autowired
    ApartmentService apartmentService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        logger.info("Request for get all apartments");

        return new ResponseEntity(apartmentService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/{apartmentClassId}")
    public ResponseEntity create(@RequestBody Apartment apartment, @PathVariable Long apartmentClassId) {
        logger.info("Request for create apartment with apartmentClassId: " + apartmentClassId);

        return new ResponseEntity(apartmentService.save(apartment, apartmentClassId),
                HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        logger.info("Request for get apartment by id: " + id);

        Apartment apartment = apartmentService.findById(id);
        if (apartment != null) {
            return new ResponseEntity(apartment, HttpStatus.OK);
        } else {
            logger.warn(String.format(APARTMENT_BY_ID_NOT_FOUND, id));

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{apartmentClassId}")
    public ResponseEntity update(@RequestBody Apartment apartment, @PathVariable Long apartmentClassId) {
        logger.info("Request for update apartment by id: " + apartment.getId() + " and apartmentClassId: " + apartmentClassId);

        boolean update = apartmentService.update(apartment, apartmentClassId);

        if (update) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            logger.warn("Apartment by id: " + apartment.getId() + " and apartmentClassId: " + apartmentClassId + " not found!");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        logger.info("Request for delete apartment by id: " +id);

        boolean delete = apartmentService.deleteById(id);
        if (delete) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            logger.warn(String.format(APARTMENT_BY_ID_NOT_FOUND, id));

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
