package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.entity.UnavailableApartment;
import com.netcracker.hotelbe.repository.UnavailableApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UnavailableApartmentService {

    @Autowired
    UnavailableApartmentRepository unavailableApartmentRepository;

    @Autowired
    ApartmentService apartmentService;

    public List<UnavailableApartment> getAll() {
        return unavailableApartmentRepository.findAll();
    }

    public Long save(UnavailableApartment unavailableApartment, Long apartmentId) {
        Apartment apartment = apartmentService.getOne(apartmentId);
        unavailableApartment.setApartment(apartment);
        UnavailableApartment save = unavailableApartmentRepository.save(unavailableApartment);
        return save.getId();
    }

    public UnavailableApartment findById(Long id) {
        try {
            return unavailableApartmentRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean update(UnavailableApartment unavailableApartment, Long apartmentId) {
        UnavailableApartment update;
        Apartment apartment;
        try {
            update = unavailableApartmentRepository.findById(unavailableApartment.getId()).get();
            apartment = apartmentService.getOne(apartmentId);
        } catch (NoSuchElementException e) {
            return false;
        }

        update.setId(unavailableApartment.getId());
        update.setStartDate(unavailableApartment.getStartDate());
        update.setEndDate(unavailableApartment.getEndDate());
        update.setCauseDescription(unavailableApartment.getCauseDescription());
        update.setApartment(apartment);

        unavailableApartmentRepository.save(update);

        return true;
    }

    public boolean deleteById(Long id) {
        UnavailableApartment delete;
        try {
            delete = unavailableApartmentRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return false;
        }

        unavailableApartmentRepository.delete(delete);
        return true;
    }
}
