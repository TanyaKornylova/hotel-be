package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.component.ApartmentClassComponent;
import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.service.ApartmentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apartments")
public class ApartmentClassController {

    @Autowired
    ApartmentClassService apartmentClassService;

    @GetMapping
    public ResponseEntity getAllApartmentClass(){
        return new ResponseEntity(apartmentClassService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addApartmentClass(@RequestBody ApartmentClass apartmentClass){
        apartmentClassService.save(apartmentClass);
        System.out.println(apartmentClass.getId());
        return new ResponseEntity(apartmentClass, HttpStatus.OK);
    }

}
