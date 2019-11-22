package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.repository.ApartmentClassRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ApartmentClassService {
    private static Logger logger = LogManager.getLogger(ApartmentClassService.class);

    @Autowired
    private ApartmentClassRepository apartmentClassRepository;


    public List<ApartmentClass> findAll() {
        logger.trace("Find all ApartmentClass");

        final List<ApartmentClass> apartmentClasses = apartmentClassRepository.findAll();
        logger.info("Found " + apartmentClasses.size() + " elements");

        return apartmentClasses;
    }

    public Long save(ApartmentClass apartmentClass) {
        logger.trace("Save ApartmentClass");

        final ApartmentClass save = apartmentClassRepository.save(apartmentClass);
        final long id = save.getId();
        logger.trace("Save apartment class with id " + id);

        return id;
    }

    public ApartmentClass findById(final Long id) {
        logger.trace("Find ApartmentClass by id " + id);

        return apartmentClassRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );
    }

    public Long update(ApartmentClass apartmentClass) {
        logger.trace("Update apartment class");
        final Long apartmentClassId = apartmentClass.getId();

        ApartmentClass update = apartmentClassRepository.findById(apartmentClassId).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(apartmentClassId))
        );
        logger.trace("Found apartment class with id = " + apartmentClassId);

        update.setNameClass(apartmentClass.getNameClass());
        update.setNumberOfRooms(apartmentClass.getNumberOfRooms());
        update.setNumberOfCouchette(apartmentClass.getNumberOfCouchette());

        update = apartmentClassRepository.save(update);
        logger.trace("Updated apartment class is saved");

        return update.getId();
    }

    public void deleteById(final Long id) {
        logger.trace("Delete apartment class by id " + id);

        ApartmentClass delete = apartmentClassRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );
        logger.trace("Found apartment class for delete");

        apartmentClassRepository.delete(delete);
        logger.trace("Apartment class deleted");
    }

}
