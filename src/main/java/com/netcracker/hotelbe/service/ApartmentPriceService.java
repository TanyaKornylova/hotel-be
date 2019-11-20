package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.entity.ApartmentPrice;
import com.netcracker.hotelbe.repository.ApartmentPriceRepository;
import com.netcracker.hotelbe.utils.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ApartmentPriceService {
    private SimpleLogger logger = new SimpleLogger(ApartmentPriceService.class);

    @Autowired
    private ApartmentPriceRepository apartmentPriceRepository;

    @Autowired
    private ApartmentService apartmentService;

    public List<ApartmentPrice> getAll() {
        logger.trace("Find all ApartmentPrice");

        List<ApartmentPrice> apartmentPrices = apartmentPriceRepository.findAll();
        logger.info("Found " + apartmentPrices.size() + " elements");

        return apartmentPrices;
    }

    public Long save(ApartmentPrice apartmentPrice, Long apartmentId) {
        logger.trace("Save ApartmentPrice");

        Apartment apartment = apartmentService.getOne(apartmentId);
        apartmentPrice.setApartment(apartment);

        ApartmentPrice save = apartmentPriceRepository.save(apartmentPrice);
        long id = save.getId();
        logger.trace("Save apartment price with id " + id);

        return id;
    }

    public ApartmentPrice findById(Long id) {
        logger.trace("Find apartment price by id " + id);

        ApartmentPrice apartmentPrice;

        try {
            apartmentPrice = apartmentPriceRepository.findById(id).get();
            logger.trace("Found apartment price");
        } catch (NoSuchElementException noSuchElement) {
            logger.error("Apartment price with id " + id + " not found!", noSuchElement);

            apartmentPrice = null;
        }

        return apartmentPrice;
    }

    public boolean update(ApartmentPrice apartmentPrice, Long apartmentId) {
        logger.trace("Update apartment price" );

        ApartmentPrice update;
        Apartment apartment;
        boolean result;

        try {
            update = apartmentPriceRepository.findById(apartmentPrice.getId()).get();
            logger.trace("Found apartment price");

            apartment = apartmentService.getOne(apartmentId);
            logger.trace("Found apartment");

            update.setId(apartmentPrice.getId());
            update.setPrice(apartmentPrice.getPrice());
            update.setStartPeriod(apartmentPrice.getStartPeriod());
            update.setEndPeriod(apartmentPrice.getEndPeriod());
            update.setApartment(apartment);
            apartmentPriceRepository.save(update);
            logger.trace("Updated apartment price is saved");

            result = true;
        } catch (NoSuchElementException noSuchElement) {
            logger.error("Apartment price with id " + apartmentPrice.getId() + " not found!", noSuchElement);

            result = false;
        }

        return result;
    }

    public boolean deleteById(Long id) {
        logger.trace("Delete apartment price by id " + id);

        ApartmentPrice delete;
        boolean result;

        try {
            delete = apartmentPriceRepository.findById(id).get();
            logger.trace("Found apartment price for delete");

            apartmentPriceRepository.delete(delete);
            logger.trace("Apartment price deleted");

            result = true;
        } catch (NoSuchElementException noSuchElement) {
            logger.error("Apartment price with id " + id + " not found!", noSuchElement);

            result = false;
        }

        return result;
    }
}
