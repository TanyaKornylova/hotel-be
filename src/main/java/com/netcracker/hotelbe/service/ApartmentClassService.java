package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.repository.ApartmentClassRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ApartmentClassService {
    private Logger logger = LogManager.getLogger(ApartmentPriceService.class);

    @Autowired
    private ApartmentClassRepository apartmentClassRepository;

    public List<ApartmentClass> getAll() {
        if (logger.isTraceEnabled()) {
            logger.trace("Find all ApartmentClass");
        }

        if (logger.isInfoEnabled()) {
            logger.info("get all info");
        }

        return apartmentClassRepository.findAll();
    }

    public Long save(ApartmentClass apartmentClass) {
        if (logger.isTraceEnabled()) {
            logger.trace("Save ApartmentClass "+ apartmentClass.toString());
        }

        ApartmentClass save = apartmentClassRepository.save(apartmentClass);

        long id = save.getId();

        if (logger.isTraceEnabled()) {
            logger.trace("Save apartment class with id "+ id);
        }

        return id;
    }

    public ApartmentClass findById(Long id) {
        if (logger.isTraceEnabled()) {
            logger.trace("Find apartment class by id " + id);
        }

        ApartmentClass apartmentClass;

        try {
            apartmentClass = apartmentClassRepository.findById(id).get();

            if (logger.isTraceEnabled()) {
                logger.trace("Found apartment class " + apartmentClass.toString());
            }

        } catch (NoSuchElementException e) {
            if (logger.isEnabledFor(Priority.ERROR)) {
                logger.trace("Apartment class with id "+id+" not found!");
            }

            apartmentClass = null;
        }

        return apartmentClass;
    }

    public boolean update(ApartmentClass apartmentClass) {
        if (logger.isTraceEnabled()) {
            logger.trace("Update apartment class "+ apartmentClass.toString());
        }

        ApartmentClass update;

        boolean result;

        try {
            if (logger.isTraceEnabled()) {
                logger.trace("Find apartment class by id "+apartmentClass.getId());
            }

            update = apartmentClassRepository.findById(apartmentClass.getId()).get();

            if (logger.isTraceEnabled()) {
                logger.trace("Found apartment class "+ update.toString());
            }

            update.setId(apartmentClass.getId());
            update.setNameClass(apartmentClass.getNameClass());
            update.setNumberOfRooms(apartmentClass.getNumberOfRooms());
            update.setNumberOfCouchette(apartmentClass.getNumberOfCouchette());

            if (logger.isTraceEnabled()) {
                logger.trace("Apartment class "+update.toString()+" is updated");
            }

            apartmentClassRepository.save(update);

            if (logger.isTraceEnabled()) {
                logger.trace("Updated apartment class is saved");
            }

            result = true;
        } catch (NoSuchElementException e) {
            if (logger.isEnabledFor(Priority.ERROR)) {
                logger.trace("Apartment class with id " + apartmentClass.getId()+ " not found!");
            }

            result = false;
        }

        return result;
    }

    public boolean deleteById(Long id) {
        ApartmentClass delete;
        try {
            delete = apartmentClassRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return false;
        }

        apartmentClassRepository.delete(delete);

        return true;
    }

    public ApartmentClass getOne(Long id) {
        return apartmentClassRepository.getOne(id);
    }

}
