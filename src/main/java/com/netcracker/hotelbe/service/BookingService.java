package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.entity.Booking;
import com.netcracker.hotelbe.entity.User;
import com.netcracker.hotelbe.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ApartmentClassService apartmentClassService;

    @Autowired
    ApartmentService apartmentService;

    @Autowired
    UserService userService;

    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public Booking save(final Booking booking) {
        final ApartmentClass apartmentClass = apartmentClassService.findById(booking.getApartmentClass().getId());
        booking.setApartmentClass(apartmentClass);
        final User user = userService.findById(booking.getUser().getId());
        booking.setUser(user);
        return bookingRepository.save(booking);
    }

    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );
    }

    public Booking update(Booking booking, Long id) {

        Booking update = bookingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );

        ApartmentClass apartmentClass = apartmentClassService.findById(booking.getApartmentClass().getId());
        booking.setApartmentClass(apartmentClass);
        User user = userService.findById(booking.getUser().getId());
        booking.setUser(user);

        update.setId(booking.getId());
        update.setBookingStatus(booking.getBookingStatus());
        update.setComment(booking.getComment());
        update.setCreatedDate(booking.getCreatedDate());
        update.setStartDate(booking.getStartDate());
        update.setEndDate(booking.getEndDate());
        update.setReview(booking.getReview());
        update.setTotalPrice(booking.getTotalPrice());
        update.setApartment(booking.getApartment());
        return bookingRepository.save(update);
    }

    public void deleteById(Long id) {
        Booking delete = bookingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );

        bookingRepository.delete(delete);
    }
}
