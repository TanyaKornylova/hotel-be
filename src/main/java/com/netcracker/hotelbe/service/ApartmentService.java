package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.repository.ApartmentRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ApartmentService {
    private Logger logger = LogManager.getLogger(ApartmentPriceService.class);

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ApartmentClassService apartmentClassService;

    public List<Apartment> getAll() {
        logger.trace("Find all Apartment");

        final List<Apartment> apartments = apartmentRepository.findAll();
        logger.info("Found " + apartments.size() + " elements");

        return apartments;
    }

    public Long save(Apartment apartment, Long apartmentClassId) {
        logger.trace("Save Apartment");

        final ApartmentClass apartmentClass = apartmentClassService.findById(apartmentClassId);
        long id;
        if (apartmentClass != null) {
            apartment.setApartmentClass(apartmentClass);

            final Apartment save = apartmentRepository.save(apartment);
            id = save.getId();
            logger.trace("Save apartment with id " + id);
        } else {
            id = -1;
        }
        return id;
    }

    public Apartment findById(Long id) {
        logger.trace("Find apartment by id " + id);

        Apartment apartment;

        try {
            apartment = apartmentRepository.findById(id).get();
            logger.trace("Found apartment");
        } catch (NoSuchElementException noSuchElement) {
            if (logger.isEnabledFor(Priority.ERROR)) {
                logger.error("Apartment with id " + id + " not found!", noSuchElement);
            }
            apartment = null;
        }

        return apartment;
    }

    public boolean update(Apartment apartment, Long apartmentClassId) {
        logger.trace("Update apartment");

        Apartment update;
        ApartmentClass apartmentClass;
        boolean result;

        try {
            update = apartmentRepository.findById(apartment.getId()).get();
            logger.trace("Found apartment");

            apartmentClass = apartmentClassService.findById(apartmentClassId);

            if (apartmentClass != null) {
                logger.trace("Found apartment class");
                update.setId(apartment.getId());
                update.setRoomNumber(apartment.getRoomNumber());
                update.setPhoto(apartment.getPhoto());
                update.setDescription(apartment.getDescription());
                update.setStatus(apartment.getStatus());
                update.setApartmentClass(apartmentClass);
                apartmentRepository.save(update);
                logger.trace("Updated apartment is saved");

                result = true;
            } else {
                result = false;
            }
        } catch (NoSuchElementException noSuchElement) {
            if (logger.isEnabledFor(Priority.ERROR)) {
                logger.error("Apartment with id " + apartment.getId() + " not found!", noSuchElement);
            }

            result = false;
        }

        return result;
    }

    public boolean deleteById(Long id) {
        logger.trace("Delete apartment by id " + id);

        Apartment delete;
        boolean result;

        try {
            delete = apartmentRepository.findById(id).get();
            logger.trace("Found apartment for delete");

            apartmentRepository.delete(delete);
            logger.trace("Apartment deleted");

            result = true;
        } catch (NoSuchElementException noSuchElement) {
            if (logger.isEnabledFor(Priority.ERROR)) {
                logger.error("Apartment with id " + id + " not found!", noSuchElement);
            }
            result = false;
        }

        return result;
    }

}
