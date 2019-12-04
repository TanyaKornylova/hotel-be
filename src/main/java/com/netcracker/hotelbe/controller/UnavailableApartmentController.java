package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.UnavailableApartment;
import com.netcracker.hotelbe.service.UnavailableApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unavailableApartments")
public class UnavailableApartmentController {

    @Autowired
    private UnavailableApartmentService unavailableApartmentService;

    @GetMapping
    public ResponseEntity<List<UnavailableApartment>> getAll() {
        return new ResponseEntity<>(unavailableApartmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnavailableApartment> getById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(unavailableApartmentService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UnavailableApartment> create(@RequestBody UnavailableApartment unavailableApartment) {
        return new ResponseEntity<>(unavailableApartmentService.save(unavailableApartment), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UnavailableApartment> update(@RequestBody UnavailableApartment unavailableApartment, @PathVariable("id") final Long id) {
        return new ResponseEntity<>(unavailableApartmentService.update(unavailableApartment, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") final Long id) {
        unavailableApartmentService.deleteById(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
