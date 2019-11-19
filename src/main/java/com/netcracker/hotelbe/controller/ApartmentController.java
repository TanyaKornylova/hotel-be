package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.service.ApartmentService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apartment")
public class ApartmentController {
    private Logger LOGGER = LogManager.getLogger(ApartmentController.class);
    private final static String APARTMENT_BY_ID_NOT_FOUND = "Apartment by id: %d not found!";

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Request for get all apartments");
        }

        return new ResponseEntity(apartmentService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/{apartmentClassId}")
    public ResponseEntity create(@RequestBody Apartment apartment, @PathVariable Long apartmentClassId) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Request for create apartment with apartmentClassId: " + apartmentClassId);
        }

        return new ResponseEntity(apartmentService.save(apartment, apartmentClassId),
                HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Request for get apartment by id: " + id);
        }

        Apartment apartment = apartmentService.findById(id);
        if (apartment != null) {
            return new ResponseEntity(apartment, HttpStatus.OK);
        } else {
            if (LOGGER.isEnabledFor(Priority.WARN)) {
                LOGGER.warn(String.format(APARTMENT_BY_ID_NOT_FOUND, id));
            }

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{apartmentClassId}")
    public ResponseEntity update(@RequestBody Apartment apartment, @PathVariable Long apartmentClassId) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Request for update apartment by id: " + apartment.getId() + " and apartmentClassId: " + apartmentClassId);
        }

        boolean update = apartmentService.update(apartment, apartmentClassId);

        if (update) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            if (LOGGER.isEnabledFor(Priority.WARN)) {
                LOGGER.warn("Apartment by id: " + apartment.getId() + " and apartmentClassId: " + apartmentClassId + " not found!");
            }

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Request for delete apartment by id: " + id);
        }

        boolean delete = apartmentService.deleteById(id);
        if (delete) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            if (LOGGER.isEnabledFor(Priority.WARN)) {
                LOGGER.warn(String.format(APARTMENT_BY_ID_NOT_FOUND, id));
            }

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
