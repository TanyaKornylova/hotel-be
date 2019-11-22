package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.service.ApartmentClassService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apartment-class")
public class ApartmentClassController {
    private static Logger logger = LogManager.getLogger(ApartmentClassController.class);
    private final static String APARTMENT_CLASS_BY_ID_NOT_FOUND = "Apartment class by id: %d not found!";

    @Autowired
    private ApartmentClassService apartmentClassService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        logger.info("Request for get all apartment classes");

        return new ResponseEntity(apartmentClassService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        logger.info("Request for get apartment class by Id: " + id);

        final ApartmentClass apartmentClass = apartmentClassService.findById(id);
        if (apartmentClass != null) {
            return new ResponseEntity(apartmentClass, HttpStatus.OK);
        } else {
            logger.warn(String.format(APARTMENT_CLASS_BY_ID_NOT_FOUND, id));

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ApartmentClass apartmentClass) {
        logger.info("Request for create apartment class");

        return new ResponseEntity(apartmentClassService.save(apartmentClass),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody ApartmentClass apartmentClass) {
        logger.info("Request for update apartment class by id: " + apartmentClass.getId());

        final boolean update = apartmentClassService.update(apartmentClass);

        if (update) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            logger.warn(String.format(APARTMENT_CLASS_BY_ID_NOT_FOUND, apartmentClass.getId()));

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        logger.info("Request for delete apartment class by id: " + id);

        final boolean delete = apartmentClassService.deleteById(id);
        if (delete) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            logger.warn(String.format(APARTMENT_CLASS_BY_ID_NOT_FOUND, id));

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
