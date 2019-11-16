package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.Apartment;
import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.repository.ApartmentClassRepository;
import com.netcracker.hotelbe.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentService {

    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    ApartmentClassService apartmentClassService;

    public List<Apartment> getAll(){
        return apartmentRepository.findAll();
    }

    public boolean addApartment(Apartment apartment, Long apartmentClassId){
        ApartmentClass apartmentClass = apartmentClassService.getOne(apartmentClassId);
        if(apartment!=null && apartmentClass!=null){
           apartment.setApartmentClass(apartmentClass);
           apartmentRepository.save(apartment);
           return true;
        } else {
            return false;
        }
    }

    public Apartment getOne(Long id){
        return apartmentRepository.getOne(id);
    }
}
