package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.repository.ApartmentClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ApartmentClassService {

    @Autowired
    ApartmentClassRepository apartmentClassRepository;

    public List<ApartmentClass> getAll(){
        return apartmentClassRepository.findAll();
    }

    public Long save(ApartmentClass apartmentClass) {
        ApartmentClass save = apartmentClassRepository.save(apartmentClass);

        return save.getId();
    }

    public ApartmentClass findById(Long id) {

        try {
            return apartmentClassRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean update(ApartmentClass apartmentClass) {
        ApartmentClass update;

        try {
            update = apartmentClassRepository.findById(apartmentClass.getId()).get();
        } catch (NoSuchElementException e) {
            return false;
        }

        update.setId(apartmentClass.getId());
        update.setNameClass(apartmentClass.getNameClass());
        update.setNumberOfRooms(apartmentClass.getNumberOfRooms());
        update.setNumberOfCouchette(apartmentClass.getNumberOfCouchette());

        apartmentClassRepository.save(update);

        return true;
    }

    public boolean deleteById(Long id) {
        ApartmentClass delete;
        try {
            delete = apartmentClassRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return false;
        }

        apartmentClassRepository.delete(delete);

        return true;
    }

    public ApartmentClass getOne(Long id){
        return apartmentClassRepository.getOne(id);
    }

}
