package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.repository.ApartmentRepository;
import com.netcracker.hotelbe.utils.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ApartmentService {
    private SimpleLogger logger = new SimpleLogger(ApartmentPriceService.class);

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

        final ApartmentClass apartmentClass = apartmentClassService.getOne(apartmentClassId);
        apartment.setApartmentClass(apartmentClass);

        final Apartment save = apartmentRepository.save(apartment);
        final long id = save.getId();
        logger.trace("Save apartment with id " + id);

        return id;
    }

    public Apartment findById(Long id) {
        logger.trace("Find apartment by id " + id);

        Apartment apartment;

        try {
            apartment = apartmentRepository.findById(id).get();
            logger.trace("Found apartment");
        } catch (NoSuchElementException noSuchElement) {
            logger.error("Apartment with id " + id + " not found!", noSuchElement);

            apartment = null;
        }

        return apartment;
    }

    public boolean update(Apartment apartment, Long apartmentClassId) {
        logger.trace("Update apartment " + apartment.toString());

        Apartment update;
        ApartmentClass apartmentClass;
        boolean result;

        try {
            update = apartmentRepository.findById(apartment.getId()).get();
            logger.trace("Found apartment");

            apartmentClass = apartmentClassService.getOne(apartmentClassId);
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
        } catch (NoSuchElementException noSuchElement) {
            logger.error("Apartment with id " + apartment.getId() + " not found!", noSuchElement);

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
            logger.error("Apartment with id " + id + " not found!", noSuchElement);

            result = false;
        }

        return result;
    }

    public Apartment getOne(Long id) {
        logger.trace("Get one apartment by id " + id);

        return apartmentRepository.getOne(id);
    }
}
