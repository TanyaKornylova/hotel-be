package com.netcracker.hotelbe.repository;

import com.netcracker.hotelbe.entity.BookingAddServicesShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BookingAddServicesShipRepository extends JpaRepository<BookingAddServicesShip, Long> {

    @Transactional(readOnly = true)
    @Override
    Optional<BookingAddServicesShip> findById(Long id);
}
