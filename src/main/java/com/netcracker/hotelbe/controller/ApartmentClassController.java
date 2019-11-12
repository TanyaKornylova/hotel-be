package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.component.ApartmentClassComponent;
import com.netcracker.hotelbe.entity.ApartmentClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApartmentClassController {

    @Autowired
    ApartmentClassComponent apartmentClassComponent;

    @GetMapping("/apartments")
    public ResponseEntity getAllApartmentClass(){
        return new ResponseEntity(apartmentClassComponent.getAll(), HttpStatus.OK);
    }

}
