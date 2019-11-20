package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.entity.UnavailableApartment;
import com.netcracker.hotelbe.repository.UnavailableApartmentRepository;
import com.netcracker.hotelbe.utils.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UnavailableApartmentService {
    private SimpleLogger logger = new SimpleLogger(ApartmentPriceService.class);

    @Autowired
    private UnavailableApartmentRepository unavailableApartmentRepository;

    @Autowired
    private ApartmentService apartmentService;

    public List<UnavailableApartment> getAll() {
        logger.trace("Find all Unavailable Apartment");

        List<UnavailableApartment> unavailableApartments = unavailableApartmentRepository.findAll();
        logger.info("Found " + unavailableApartments.size() + " elements");

        return unavailableApartments;
    }

    public Long save(UnavailableApartment unavailableApartment, Long apartmentId) {
        logger.trace("Save UnavailableApartment");

        Apartment apartment = apartmentService.getOne(apartmentId);
        unavailableApartment.setApartment(apartment);
        UnavailableApartment save = unavailableApartmentRepository.save(unavailableApartment);

        long id = save.getId();
        logger.trace("Save apartment with id " + id);

        return id;
    }

    public UnavailableApartment findById(Long id) {
        logger.trace("Find unavailable apartment by id " + id);

        UnavailableApartment unavailableApartment;

        try {
            unavailableApartment = unavailableApartmentRepository.findById(id).get();
            logger.trace("Found unavailable apartment");
        } catch (NoSuchElementException noSuchElement) {
            logger.error("Unavailable Apartment with id " + id + " not found!", noSuchElement);

            unavailableApartment = null;
        }

        return unavailableApartment;
    }

    public boolean update(UnavailableApartment unavailableApartment, Long apartmentId) {
        logger.trace("Update UnavailableApartment");

        UnavailableApartment update;
        Apartment apartment;
        boolean result;

        try {
            update = unavailableApartmentRepository.findById(unavailableApartment.getId()).get();
            logger.trace("Found UnavailableApartment " + update.toString());

            apartment = apartmentService.getOne(apartmentId);
            logger.trace("Found apartment " + apartment.toString());

            update.setId(unavailableApartment.getId());
            update.setStartDate(unavailableApartment.getStartDate());
            update.setEndDate(unavailableApartment.getEndDate());
            update.setCauseDescription(unavailableApartment.getCauseDescription());
            update.setApartment(apartment);
            unavailableApartmentRepository.save(update);
            logger.trace("Updated UnavailableApartment is saved");

            result = true;
        } catch (NoSuchElementException noSuchElement) {
            logger.error("Apartment with id " + unavailableApartment.getId() + " not found!", noSuchElement);

            result = false;
        }

        return result;
    }

    public boolean deleteById(Long id) {
        logger.trace("Delete UnavailableApartment by id " + id);

        UnavailableApartment delete;
        boolean result;

        try {
            delete = unavailableApartmentRepository.findById(id).get();
            logger.trace("Found UnavailableApartment for delete");

            unavailableApartmentRepository.delete(delete);
            logger.trace("UnavailableApartment deleted");

            result = true;
        } catch (NoSuchElementException noSuchElement) {
            logger.error("UnavailableApartment with id " + id + " not found!", noSuchElement);

            result = false;
        }

        return result;
    }
}
