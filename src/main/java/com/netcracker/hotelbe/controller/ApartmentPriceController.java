package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.ApartmentPrice;
import com.netcracker.hotelbe.service.ApartmentPriceService;
import com.netcracker.hotelbe.utils.CustomEntityMessage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apartment-price")
public class ApartmentPriceController {
    private static Logger logger = LogManager.getLogger(ApartmentPriceController.class);
    private final static String ENTITY_NAME = ApartmentPrice.class.getSimpleName();

    @Autowired
    private ApartmentPriceService apartmentPriceService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        logger.info(String.format(CustomEntityMessage.REQUEST_FOR_GET_ALL_ENTITY, ENTITY_NAME));

        return new ResponseEntity(apartmentPriceService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable("id") final Long id) {
        logger.info(String.format(CustomEntityMessage.REQUEST_FOR_GET_ENTITY_BY_ID, ENTITY_NAME, id));

        return new ResponseEntity(apartmentPriceService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/{apartmentId}")
    public ResponseEntity create(@RequestBody ApartmentPrice apartmentPrice, @PathVariable("apartmentId") final Long apartmentId) {
        logger.info(String.format(CustomEntityMessage.REQUEST_FOR_CREATE_ENTITY, ENTITY_NAME));

        return new ResponseEntity(apartmentPriceService.save(apartmentPrice, apartmentId), HttpStatus.CREATED);
    }

    @PutMapping("{apartmentId}")
    public ResponseEntity update(@RequestBody ApartmentPrice apartmentPrice, @PathVariable("apartmentId") final Long apartmentId) {
        logger.info(String.format(CustomEntityMessage.REQUEST_FOR_UPDATE_ENTITY_BY_ID, ENTITY_NAME, apartmentPrice.getId()));

        return new ResponseEntity(apartmentPriceService.update(apartmentPrice, apartmentId), HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") final Long id) {
        logger.info(String.format(CustomEntityMessage.REQUEST_FOR_DELETE_ENTITY_BY_ID, ENTITY_NAME, id));

        apartmentPriceService.deleteById(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
