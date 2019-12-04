package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.entity.ApartmentPrice;
import com.netcracker.hotelbe.repository.ApartmentPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ApartmentPriceService {

    @Autowired
    private ApartmentPriceRepository apartmentPriceRepository;

    @Autowired
    private ApartmentClassService apartmentClassService;

    public List<ApartmentPrice> findAll() {
        return apartmentPriceRepository.findAll();
    }

    public ApartmentPrice save(ApartmentPrice apartmentPrice) {
        final ApartmentClass apartmentClass = apartmentClassService.findById(apartmentPrice.getApartmentClass().getId());
        apartmentPrice.setApartmentClass(apartmentClass);

        return apartmentPriceRepository.save(apartmentPrice);
    }

    public ApartmentPrice findById(final Long id) {
        return apartmentPriceRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );
    }

    public ApartmentPrice update(final ApartmentPrice apartmentPrice, final Long id) {
        final ApartmentClass apartmentClass = apartmentClassService.findById(apartmentPrice.getApartmentClass().getId());

        ApartmentPrice update = apartmentPriceRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );

        update.setPrice(apartmentPrice.getPrice());
        update.setStartPeriod(apartmentPrice.getStartPeriod());
        update.setEndPeriod(apartmentPrice.getEndPeriod());
        update.setApartmentClass(apartmentClass);

        return apartmentPriceRepository.save(update);
    }

    public void deleteById(final Long id) {
        final ApartmentPrice delete = apartmentPriceRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );

        apartmentPriceRepository.delete(delete);
    }
}
