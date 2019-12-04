package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ApartmentClassService apartmentClassService;

    public List<Apartment> getAll() {
        return apartmentRepository.findAll();
    }

    public Apartment save(Apartment apartment) {
        final ApartmentClass apartmentClass = apartmentClassService.findById(apartment.getApartmentClass().getId());
        apartment.setApartmentClass(apartmentClass);

        return apartmentRepository.save(apartment);
    }

    public Apartment findById(final Long id) {
        return apartmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );
    }

    public Apartment update(final Apartment apartment, final Long id) {
        final ApartmentClass apartmentClass = apartmentClassService.findById(apartment.getApartmentClass().getId());

        Apartment update = apartmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );

        update.setRoomNumber(apartment.getRoomNumber());
        update.setPhoto(apartment.getPhoto());
        update.setDescription(apartment.getDescription());
        update.setStatus(apartment.getStatus());
        update.setApartmentClass(apartmentClass);

        return apartmentRepository.save(update);
    }

    public void deleteById(final Long id) {

        final Apartment delete = apartmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );

        apartmentRepository.delete(delete);
    }
}
