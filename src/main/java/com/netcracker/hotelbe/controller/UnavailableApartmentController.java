package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.UnavailableApartment;
import com.netcracker.hotelbe.service.UnavailableApartmentService;
import com.netcracker.hotelbe.utils.CustomEntityLogMessage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("unavailable-apartment")
public class UnavailableApartmentController {
    private static Logger logger = LogManager.getLogger(UnavailableApartmentController.class);
    private final static String ENTITY_NAME = UnavailableApartment.class.getSimpleName();

    @Autowired
    private UnavailableApartmentService unavailableApartmentService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        logger.info(String.format(CustomEntityLogMessage.REQUEST_FOR_GET_ALL_ENTITY, ENTITY_NAME));

        return new ResponseEntity(unavailableApartmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") final Long id) {
        logger.info(String.format(CustomEntityLogMessage.REQUEST_FOR_GET_ENTITY_BY_ID, ENTITY_NAME, id));

        return new ResponseEntity(unavailableApartmentService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/{apartmentId}")
    public ResponseEntity create(@RequestBody UnavailableApartment unavailableApartment, @PathVariable("apartmentId") final Long apartmentId) {
        logger.info(String.format(CustomEntityLogMessage.REQUEST_FOR_CREATE_ENTITY, ENTITY_NAME));

        return new ResponseEntity(unavailableApartmentService.save(unavailableApartment, apartmentId), HttpStatus.CREATED);
    }


    @PutMapping("/{apartmentId}")
    public ResponseEntity update(@RequestBody UnavailableApartment unavailableApartment, @PathVariable("apartmentId") final Long apartmentId) {
        logger.info(String.format(CustomEntityLogMessage.REQUEST_FOR_UPDATE_ENTITY_BY_ID, ENTITY_NAME, unavailableApartment.getId()));

        return new ResponseEntity(unavailableApartmentService.update(unavailableApartment, apartmentId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") final Long id) {
        logger.info(String.format(CustomEntityLogMessage.REQUEST_FOR_DELETE_ENTITY_BY_ID, ENTITY_NAME, id));

        unavailableApartmentService.deleteById(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
