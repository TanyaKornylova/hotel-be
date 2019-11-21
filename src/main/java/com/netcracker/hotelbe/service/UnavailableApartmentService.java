package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.entity.UnavailableApartment;
import com.netcracker.hotelbe.repository.UnavailableApartmentRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UnavailableApartmentService {
    private Logger logger = LogManager.getLogger(ApartmentPriceService.class);

    @Autowired
    private UnavailableApartmentRepository unavailableApartmentRepository;

    @Autowired
    private ApartmentService apartmentService;

    public List<UnavailableApartment> getAll() {
        logger.trace("Find all Unavailable Apartment");

        final List<UnavailableApartment> unavailableApartments = unavailableApartmentRepository.findAll();
        logger.info("Found " + unavailableApartments.size() + " elements");

        return unavailableApartments;
    }

    public Long save(UnavailableApartment unavailableApartment, Long apartmentId) {
        logger.trace("Save UnavailableApartment");

        final Apartment apartment = apartmentService.findById(apartmentId);
        long id;
        if (apartment != null) {
            unavailableApartment.setApartment(apartment);
            final UnavailableApartment save = unavailableApartmentRepository.save(unavailableApartment);

            id = save.getId();
            logger.trace("Save apartment with id " + id);
        } else {
            id = 1;
        }
        return id;
    }

    public UnavailableApartment findById(Long id) {
        logger.trace("Find unavailable apartment by id " + id);

        UnavailableApartment unavailableApartment;

        try {
            unavailableApartment = unavailableApartmentRepository.findById(id).get();
            logger.trace("Found unavailable apartment");
        } catch (NoSuchElementException noSuchElement) {
            if (logger.isEnabledFor(Priority.ERROR)) {
                logger.error("Unavailable Apartment with id " + id + " not found!", noSuchElement);
            }
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
            logger.trace("Found UnavailableApartment");

            apartment = apartmentService.findById(apartmentId);

            if (apartment != null) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Found apartment");
                }
                update.setId(unavailableApartment.getId());
                update.setStartDate(unavailableApartment.getStartDate());
                update.setEndDate(unavailableApartment.getEndDate());
                update.setCauseDescription(unavailableApartment.getCauseDescription());
                update.setApartment(apartment);
                unavailableApartmentRepository.save(update);
                logger.trace("Updated UnavailableApartment is saved");

                result = true;
            } else {
                result = false;
            }
        } catch (NoSuchElementException noSuchElement) {
            if (logger.isEnabledFor(Priority.ERROR)) {
                logger.error("Apartment with id " + unavailableApartment.getId() + " not found!", noSuchElement);
            }
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
            if (logger.isEnabledFor(Priority.ERROR)) {
                logger.error("UnavailableApartment with id " + id + " not found!", noSuchElement);
            }
            result = false;
        }

        return result;
    }
}
