package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.repository.ApartmentClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ApartmentClassService {

    @Autowired
    private ApartmentClassRepository apartmentClassRepository;


    public List<ApartmentClass> findAll() {
        return  apartmentClassRepository.findAll();
    }

    public ApartmentClass save(final ApartmentClass apartmentClass) {
        return apartmentClassRepository.save(apartmentClass);
    }

    public ApartmentClass findById(final Long id) {
        return apartmentClassRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );
    }

    public ApartmentClass update(final ApartmentClass apartmentClass, final Long id) {
        ApartmentClass update = apartmentClassRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );

        update.setNameClass(apartmentClass.getNameClass());
        update.setNumberOfRooms(apartmentClass.getNumberOfRooms());
        update.setNumberOfCouchette(apartmentClass.getNumberOfCouchette());

        return apartmentClassRepository.save(update);
    }

    public void deleteById(final Long id) {
        ApartmentClass delete = apartmentClassRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id))
        );

        apartmentClassRepository.delete(delete);
    }
}
