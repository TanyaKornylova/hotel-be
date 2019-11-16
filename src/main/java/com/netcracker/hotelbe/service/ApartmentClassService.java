package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.repository.ApartmentClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentClassService {

    @Autowired
    ApartmentClassRepository apartmentClassRepository;

    public List<ApartmentClass> getAll(){
        return apartmentClassRepository.findAll();
    }

    public void save(ApartmentClass apartmentClass){
        apartmentClassRepository.save(apartmentClass);
    }

    public ApartmentClass getOne(Long id){
        return apartmentClassRepository.getOne(id);
    }

    public Optional<ApartmentClass> getById(Long id){
        return apartmentClassRepository.findById(id);
    }

}
