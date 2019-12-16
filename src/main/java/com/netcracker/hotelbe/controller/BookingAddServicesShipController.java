package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.BookingAddServices;
import com.netcracker.hotelbe.entity.BookingAddServicesShip;
import com.netcracker.hotelbe.service.BookingAddServicesShipService;
import com.netcracker.hotelbe.utils.RuntimeExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("bookingAddServicesShip")
public class BookingAddServicesShipController {

    @Autowired
    BookingAddServicesShipService bookingAddServicesShipService;

    @GetMapping
    public ResponseEntity<List<BookingAddServicesShip>> getAllBookingAddServicesShip() {
        return new ResponseEntity<>(bookingAddServicesShipService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingAddServicesShip> getBookingAddServiceShipById(@PathVariable Long id) {
        return new ResponseEntity<>(bookingAddServicesShipService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> addBookingAddServiceShip(@RequestBody @Valid BookingAddServicesShip bookingAddServicesShip) {
        try {
            return new ResponseEntity<>(bookingAddServicesShipService.save(bookingAddServicesShip).getId(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return RuntimeExceptionHandler.handlePSQLException(e);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<BookingAddServicesShip> updateBookingAddServiceShip(@RequestBody @Valid BookingAddServicesShip bookingAddServicesShip, @PathVariable Long id) {
        bookingAddServicesShip.setId(id);
        try {
            return new ResponseEntity<>(bookingAddServicesShipService.save(bookingAddServicesShip), HttpStatus.OK);
        } catch (RuntimeException e) {
            return RuntimeExceptionHandler.handlePSQLException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBookingAddServiceShip(@PathVariable Long id) {
        bookingAddServicesShipService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
