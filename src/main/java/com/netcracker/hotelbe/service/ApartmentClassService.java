package com.netcracker.hotelbe.service;

import com.netcracker.hotelbe.entity.ApartmentClass;
import com.netcracker.hotelbe.repository.ApartmentClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentClassService {

    @Autowired
    ApartmentClassRepository apartmentClassRepository;

    public List<ApartmentClass> getAll(){
        return apartmentClassRepository.findAll();
    }
}
