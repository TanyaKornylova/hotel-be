package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.service.ApartmentService;
import com.netcracker.hotelbe.utils.CustomEntityMessage;
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
    private final static String ENTITY_NAME = Apartment.class.getSimpleName();

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        logger.info(String.format(CustomEntityMessage.REQUEST_FOR_GET_ALL_ENTITY, ENTITY_NAME));

        return new ResponseEntity(apartmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") final Long id) {
        logger.info(String.format(CustomEntityMessage.REQUEST_FOR_GET_ENTITY_BY_ID, ENTITY_NAME, id));

        return new ResponseEntity(apartmentService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/{apartmentClassId}")
    public ResponseEntity create(@RequestBody @Valid Apartment apartment, @PathVariable("apartmentClassId") final Long apartmentClassId) {
        logger.info(String.format(CustomEntityMessage.REQUEST_FOR_CREATE_ENTITY, ENTITY_NAME));

        return new ResponseEntity(apartmentService.save(apartment, apartmentClassId), HttpStatus.CREATED);
    }

    @PutMapping("/{apartmentClassId}")
    public ResponseEntity update(@RequestBody @Valid Apartment apartment, @PathVariable("apartmentClassId") final Long apartmentClassId) {
        logger.info(String.format(CustomEntityMessage.REQUEST_FOR_UPDATE_ENTITY_BY_ID, ENTITY_NAME, apartment.getId()));

        return new ResponseEntity(apartmentService.update(apartment, apartmentClassId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") final Long id) {
        logger.info(String.format(CustomEntityMessage.REQUEST_FOR_DELETE_ENTITY_BY_ID, ENTITY_NAME, id));

        apartmentService.deleteById(id);

        return new ResponseEntity(HttpStatus.OK);
    }

}
