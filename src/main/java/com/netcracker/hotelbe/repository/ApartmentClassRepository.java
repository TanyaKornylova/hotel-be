package com.netcracker.hotelbe.repository;

import com.netcracker.hotelbe.entity.ApartmentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ApartmentClassRepository extends JpaRepository<ApartmentClass, Long> {

    @Transactional(readOnly = true)
    @Override
    Optional<ApartmentClass> findById(Long id);
}
