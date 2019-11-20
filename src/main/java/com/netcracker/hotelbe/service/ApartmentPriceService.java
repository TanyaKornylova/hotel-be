package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.entity.ApartmentPrice;
import com.netcracker.hotelbe.repository.ApartmentPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ApartmentPriceService {

    @Autowired
    private ApartmentPriceRepository apartmentPriceRepository;

    @Autowired
    private ApartmentService apartmentService;

    public List<ApartmentPrice> getAll() {
        return apartmentPriceRepository.findAll();
    }

    public Long save(ApartmentPrice apartmentPrice, Long apartmentId) {
        Apartment apartment = apartmentService.getOne(apartmentId);
        apartmentPrice.setApartment(apartment);
        ApartmentPrice save = apartmentPriceRepository.save(apartmentPrice);
        return save.getId();
    }

    public ApartmentPrice findById(Long id) {
        try {
            return apartmentPriceRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean update(ApartmentPrice apartmentPrice, Long apartmentId) {
        ApartmentPrice update;
        Apartment apartment;
        try {
            update = apartmentPriceRepository.findById(apartmentPrice.getId()).get();
            apartment = apartmentService.getOne(apartmentId);
        } catch (NoSuchElementException e) {
            return false;
        }

        update.setId(apartmentPrice.getId());
        update.setPrice(apartmentPrice.getPrice());
        update.setStartPeriod(apartmentPrice.getStartPeriod());
        update.setEndPeriod(apartmentPrice.getEndPeriod());
        update.setApartment(apartment);

        apartmentPriceRepository.save(update);

        return true;
    }

    public boolean deleteById(Long id) {
        ApartmentPrice delete;
        try {
            delete = apartmentPriceRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return false;
        }

        apartmentPriceRepository.delete(delete);
        return true;
    }
}
