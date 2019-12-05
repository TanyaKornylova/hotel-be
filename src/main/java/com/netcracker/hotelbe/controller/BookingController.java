package com.netcracker.hotelbe.controller;

import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.entity.Booking;
import com.netcracker.hotelbe.service.BookingService;
import com.netcracker.hotelbe.utils.CustomEntityLogMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("bookings")
public class BookingController {
    private final static Logger logger = Logger.getLogger(BookingController.class);
    private final static String BOOKING_BY_ID_NOT_FOUND = "Booking by id: %d not found!";
    private final static String ENTITY_NAME = Booking.class.getSimpleName();

    @Autowired
    BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> getAll() {
        logger.info("Request for get all bookings");

        return new ResponseEntity<>(bookingService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid Booking booking) {
        logger.info("Request for create booking");

        return new ResponseEntity<>(bookingService.save(booking),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getById(@PathVariable("id") final Long id) {
        logger.info("Request for get booking by id: " + id);

        Booking booking = bookingService.findById(id);
        if (booking != null) {
            return new ResponseEntity<>(booking, HttpStatus.OK);
        } else {
            logger.warn(String.format(BOOKING_BY_ID_NOT_FOUND, id));

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@RequestBody @Valid Booking booking) {
        logger.info("Request for update booking by id: " + booking.getId());

        Long update = bookingService.update(booking);

        if (update != 0) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            logger.warn("Booking by id: " + booking.getId());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        logger.info(String.format(CustomEntityLogMessage.REQUEST_FOR_DELETE_ENTITY_BY_ID, ENTITY_NAME, id));
        bookingService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
