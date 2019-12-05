package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.Booking;
import com.netcracker.hotelbe.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("bookings")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> getAll() {
        return new ResponseEntity<>(bookingService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Booking> create(@RequestBody @Valid Booking booking) {
        return new ResponseEntity<>(bookingService.save(booking),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(bookingService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> update(@RequestBody @Valid Booking booking, @PathVariable("id") final  Long id) {
        return new ResponseEntity<>(bookingService.update(booking, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        bookingService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
