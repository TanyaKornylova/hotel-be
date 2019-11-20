package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ApartmentClassService apartmentClassService;

    public List<Apartment> getAll() {
        return apartmentRepository.findAll();
    }

    public Long save(Apartment apartment, Long apartmentClassId) {
        ApartmentClass apartmentClass = apartmentClassService.getOne(apartmentClassId);
        apartment.setApartmentClass(apartmentClass);
        Apartment save = apartmentRepository.save(apartment);
        return save.getId();
    }

    public Apartment findById(Long id) {
        try {
            return apartmentRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean update(Apartment apartment, Long apartmentClassId) {
        Apartment update;
        ApartmentClass apartmentClass;
        try {
            update = apartmentRepository.findById(apartment.getId()).get();
            apartmentClass = apartmentClassService.getOne(apartmentClassId);
        } catch (NoSuchElementException e) {
            return false;
        }

        update.setId(apartment.getId());
        update.setRoomNumber(apartment.getRoomNumber());
        update.setPhoto(apartment.getPhoto());
        update.setDescription(apartment.getDescription());
        update.setStatus(apartment.getStatus());
        update.setApartmentClass(apartmentClass);

        apartmentRepository.save(update);

        return true;
    }

    public boolean deleteById(Long id) {
        Apartment delete;
        try {
            delete = apartmentRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return false;
        }

        apartmentRepository.delete(delete);
        return true;
    }

    public Apartment getOne(Long id) {
        return apartmentRepository.getOne(id);
    }
}
