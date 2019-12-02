package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.entity.BookingAddServices;
import com.netcracker.hotelbe.repository.BookingAddServicesRepository;
import com.netcracker.hotelbe.utils.CustomEntityLogMessage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BookingAddServicesService {
    private static Logger logger = LogManager.getLogger(BookingAddServicesService.class);
    private final static String ENTITY_NAME = BookingAddServices.class.getSimpleName();
    @Autowired
    private BookingAddServicesRepository bookingAddServicesRepository;

    public BookingAddServices findById(long id){
        return bookingAddServicesRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException(String.valueOf(id))
        );
    }

    public List<BookingAddServices> findAll(){
        return bookingAddServicesRepository.findAll();
    }

    public BookingAddServices save(BookingAddServices staff){
        return bookingAddServicesRepository.save(staff);
    }

    public void deleteById(Long id){
        logger.trace(String.format(CustomEntityLogMessage.DELETE_ENTITY_BY_ID, ENTITY_NAME, id));

        BookingAddServices delete = bookingAddServicesRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );
        logger.trace(String.format(CustomEntityLogMessage.FOUND_ENTITY_FOR_DELETE, ENTITY_NAME));
        bookingAddServicesRepository.delete(delete);

    }
}
