package com.netcracker.hotelbe.repository;

import com.netcracker.hotelbe.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
