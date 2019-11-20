package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.repository.ApartmentClassRepository;
import com.netcracker.hotelbe.utils.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ApartmentClassService {
    private SimpleLogger logger = new SimpleLogger(ApartmentClassService.class);

    @Autowired
    private ApartmentClassRepository apartmentClassRepository;

    public List<ApartmentClass> getAll() {
        logger.trace("Find all ApartmentClass");

        List<ApartmentClass> apartmentClasses = apartmentClassRepository.findAll();
        logger.info("Found " + apartmentClasses.size() + " elements");

        return apartmentClasses;
    }

    public Long save(ApartmentClass apartmentClass) {
        logger.trace("Save ApartmentClass");

        ApartmentClass save = apartmentClassRepository.save(apartmentClass);
        long id = save.getId();
        logger.trace("Save apartment class with id " + id);

        return id;
    }

    public ApartmentClass findById(Long id) {
        logger.trace("Find apartment class by id " + id);

        ApartmentClass apartmentClass;

        try {
            apartmentClass = apartmentClassRepository.findById(id).get();
            logger.trace("Found apartment class " + apartmentClass.toString());
        } catch (NoSuchElementException noSuchElement) {
            logger.error("Apartment class with id " + id + " not found!", noSuchElement);

            apartmentClass = null;
        }

        return apartmentClass;
    }

    public boolean update(ApartmentClass apartmentClass) {
        logger.trace("Update apartment class");

        ApartmentClass update;
        boolean result;

        try {
            update = apartmentClassRepository.findById(apartmentClass.getId()).get();
            logger.trace("Found apartment class");

            update.setId(apartmentClass.getId());
            update.setNameClass(apartmentClass.getNameClass());
            update.setNumberOfRooms(apartmentClass.getNumberOfRooms());
            update.setNumberOfCouchette(apartmentClass.getNumberOfCouchette());
            apartmentClassRepository.save(update);
            logger.trace("Updated apartment class is saved");

            result = true;
        } catch (NoSuchElementException noSuchElement) {
            logger.error("Apartment class with id " + apartmentClass.getId() + " not found!", noSuchElement);

            result = false;
        }

        return result;
    }

    public boolean deleteById(Long id) {
        logger.trace("Delete apartment class by id " + id);

        ApartmentClass delete;
        boolean result;

        try {
            delete = apartmentClassRepository.findById(id).get();
            logger.trace("Found apartment class for delete");

            apartmentClassRepository.delete(delete);
            logger.trace("Apartment class deleted");

            result = true;

        } catch (NoSuchElementException noSuchElement) {
            logger.error("Apartment class with id " + id + " not found!", noSuchElement);

            result = false;
        }

        return result;
    }

    public ApartmentClass getOne(Long id) {
        logger.trace("Get one apartment class by id " + id);

        return apartmentClassRepository.getOne(id);
    }

}
