package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.entity.Booking;
import com.netcracker.hotelbe.entity.User;
import com.netcracker.hotelbe.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserService userService;

    @Autowired
    ApartmentClassService apartmentClassService;

    @Autowired
    ApartmentService apartmentService;

    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public Long save(Booking booking, Long apartmentClassId, Long userId, Long apartmentId) {
        ApartmentClass apartmentClass = apartmentClassService.findById(apartmentClassId);
        User user = userService.findById(userId);
        Apartment apartment = apartmentService.findById(apartmentId);
        booking.setApartmentClass(apartmentClass);
        booking.setApartment(apartment);
        booking.setUser(user);
        Booking save = bookingRepository.save(booking);
        return save.getId();
    }

    public Booking findById(Long id) {
        try {
            return bookingRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean update(Booking booking, Long apartmentClassId, Long userId, Long apartmentId) {
        Booking update;
        ApartmentClass apartmentClass;
        User user;
        Apartment apartment;
        try {
            update = bookingRepository.findById(booking.getId()).get();
            apartmentClass = apartmentClassService.findById(apartmentClassId);
            user = userService.findById(userId);
            apartment = apartmentService.findById(apartmentId);
        } catch (NoSuchElementException e) {
            return false;
        }
        update.setId(booking.getId());
        update.setBookingStatus(booking.getBookingStatus());
        update.setComment(booking.getComment());
        update.setCreatedDate(booking.getCreatedDate());
        update.setStartDate(booking.getStartDate());
        update.setEndDate(booking.getEndDate());
        update.setReview(booking.getReview());
        update.setTotalPrice(booking.getTotalPrice());
        update.setApartmentClass(apartmentClass);
        update.setApartment(apartment);
        update.setUser(user);
        bookingRepository.save(update);

        return true;
    }

    public boolean deleteById(Long id) {
        Booking delete;
        try {
            delete = bookingRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return false;
        }

        bookingRepository.delete(delete);
        return true;
    }
}
