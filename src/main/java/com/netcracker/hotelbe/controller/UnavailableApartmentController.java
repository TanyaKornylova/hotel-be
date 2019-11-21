package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.UnavailableApartment;
import com.netcracker.hotelbe.service.UnavailableApartmentService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("unavailable-apartment")
public class UnavailableApartmentController {
    private Logger logger = LogManager.getLogger(UnavailableApartmentController.class);
    private final static String UNAVAILABLE_APARTMENT_BY_ID_NOT_FOUND = "Unavailable apartment by id: %d not found!";


    @Autowired
    private UnavailableApartmentService unavailableApartmentService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        logger.info("Request for get all unavailable apartment");

        return new ResponseEntity(unavailableApartmentService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/{apartmentId}")
    public ResponseEntity create(@RequestBody UnavailableApartment unavailableApartment,
                                 @PathVariable Long apartmentId) {
        logger.info("Request for create unavailable apartment with apartmentId: " + apartmentId);

        return new ResponseEntity(unavailableApartmentService.save(unavailableApartment, apartmentId),
                HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        logger.info("Request for get unavailable apartment by id: " + id);

        final UnavailableApartment unavailableApartment = unavailableApartmentService.findById(id);
        if (unavailableApartment != null) {
            return new ResponseEntity(unavailableApartment, HttpStatus.OK);
        } else {
            logger.warn(String.format(UNAVAILABLE_APARTMENT_BY_ID_NOT_FOUND, id));

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{apartmentId}")
    public ResponseEntity update(@RequestBody UnavailableApartment unavailableApartment,
                                 @PathVariable Long apartmentId) {
        logger.info("Request for update unavailable apartment by id: " + unavailableApartment.getId() + " and apartmentId: " + apartmentId);

        final boolean update = unavailableApartmentService.update(unavailableApartment, apartmentId);

        if (update) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            logger.warn("Unavailable apartment by id: " + unavailableApartment.getId() + " and apartmentId: " + apartmentId + " not found!");

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        logger.info("Request for delete unavailable apartment by id: " + id);

        final boolean delete = unavailableApartmentService.deleteById(id);
        if (delete) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            logger.warn(String.format(UNAVAILABLE_APARTMENT_BY_ID_NOT_FOUND, id));

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
