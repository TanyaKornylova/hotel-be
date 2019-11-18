package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.ApartmentPrice;
import com.netcracker.hotelbe.service.ApartmentPriceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apartment-price")
public class ApartmentPriceController {
    private final static Logger logger = Logger.getLogger(ApartmentPriceController.class);
    private final static String APARTMENT_PRICE_BY_ID_NOT_FOUND = "Apartment price by id: %d not found!";


    @Autowired
    ApartmentPriceService apartmentPriceService;

    @GetMapping("/all")
    public ResponseEntity getAll() {
        logger.info("Request for get all apartment prices");

        return new ResponseEntity(apartmentPriceService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/{apartmentId}")
    public ResponseEntity create(@RequestBody ApartmentPrice apartmentPrice,
                                 @PathVariable Long apartmentId) {
        logger.info("Request for create apartment price with apartmentId: " + apartmentId);

        return new ResponseEntity(apartmentPriceService.save(apartmentPrice, apartmentId),
                HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        logger.info("Request for get apartment price by id: " + id);

        ApartmentPrice apartmentPrice = apartmentPriceService.findById(id);
        if (apartmentPrice != null) {
            return new ResponseEntity(apartmentPrice, HttpStatus.OK);
        } else {
            logger.warn(String.format(APARTMENT_PRICE_BY_ID_NOT_FOUND, id));

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{apartmentId}")
    public ResponseEntity update(@RequestBody ApartmentPrice apartmentPrice,
                                 @PathVariable Long apartmentId) {
        logger.info("Request for update apartment price by id: " + apartmentPrice.getId() + " and apartmentId: " + apartmentId);

        boolean update = apartmentPriceService.update(apartmentPrice, apartmentId);

        if (update) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            logger.warn("Apartment price by id: " + apartmentPrice.getId() + " and apartmentId: " + apartmentId + " not found!");

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        logger.info("Request for delete apartment price by id: " +id);

        boolean delete = apartmentPriceService.deleteById(id);
        if (delete) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            logger.warn(String.format(APARTMENT_PRICE_BY_ID_NOT_FOUND, id));

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
