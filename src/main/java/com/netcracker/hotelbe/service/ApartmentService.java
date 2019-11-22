package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.repository.ApartmentRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ApartmentService {
    private static Logger logger = LogManager.getLogger(ApartmentPriceService.class);

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

    public Long save(Apartment apartment, final Long apartmentClassId) {
        logger.trace("Save Apartment");

        final ApartmentClass apartmentClass = apartmentClassService.findById(apartmentClassId);
        apartment.setApartmentClass(apartmentClass);

        final Apartment save = apartmentRepository.save(apartment);
        final long id = save.getId();
        logger.trace("Save apartment with id " + id);

        return id;
    }

    public Apartment findById(final Long id) {
        logger.trace("Find apartment by id " + id);

        return apartmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );
    }

    public Long update(Apartment apartment, final Long apartmentClassId) {
        logger.trace("Update apartment");
        final Long apartmentId = apartment.getId();

        ApartmentClass apartmentClass = apartmentClassService.findById(apartmentClassId);
        logger.trace("Found apartment class with id " + apartmentClassId);

        Apartment update = apartmentRepository.findById(apartmentId).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(apartmentId))
        );
        logger.trace("Found apartment with id " + apartmentId);

        update.setRoomNumber(apartment.getRoomNumber());
        update.setPhoto(apartment.getPhoto());
        update.setDescription(apartment.getDescription());
        update.setStatus(apartment.getStatus());
        update.setApartmentClass(apartmentClass);
        update = apartmentRepository.save(update);
        logger.trace("Updated apartment is saved");

        return update.getId();
}

    public void deleteById(Long id) {
        logger.trace("Delete apartment by id " + id);

        Apartment delete = apartmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );
        logger.trace("Found apartment for delete");

        apartmentRepository.delete(delete);
        logger.trace("Apartment deleted");

    }

}
