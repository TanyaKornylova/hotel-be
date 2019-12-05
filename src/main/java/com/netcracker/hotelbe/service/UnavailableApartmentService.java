package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.entity.UnavailableApartment;
import com.netcracker.hotelbe.repository.UnavailableApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UnavailableApartmentService {

    @Autowired
    private UnavailableApartmentRepository unavailableApartmentRepository;

    @Autowired
    private ApartmentService apartmentService;

    public List<UnavailableApartment> getAll() {
        return unavailableApartmentRepository.findAll();
    }

    public UnavailableApartment save(UnavailableApartment unavailableApartment) {
        final Apartment apartment = apartmentService.findById(unavailableApartment.getApartment().getId());
        unavailableApartment.setApartment(apartment);

        return unavailableApartmentRepository.save(unavailableApartment);
    }

    public UnavailableApartment findById(final Long id) {
        return unavailableApartmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );
    }

    public UnavailableApartment update(final UnavailableApartment unavailableApartment, final Long id) {
        final Apartment apartment = apartmentService.findById(unavailableApartment.getApartment().getId());
        UnavailableApartment update = unavailableApartmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );

        update.setStartDate(unavailableApartment.getStartDate());
        update.setEndDate(unavailableApartment.getEndDate());
        update.setCauseDescription(unavailableApartment.getCauseDescription());
        update.setApartment(apartment);

        return unavailableApartmentRepository.save(update);
    }

    public void deleteById(final Long id) {
        final UnavailableApartment delete = unavailableApartmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );

        unavailableApartmentRepository.delete(delete);
    }
}
