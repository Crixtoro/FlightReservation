package com.makaia.flightReservation.persistence.crud;

import com.makaia.flightReservation.persistence.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightCrudRepository extends JpaRepository<Flight, String> {

    List<Flight> findByOriginAndDestination(String origin, String destination);
    Page<Flight> findByOriginAndDestination(String origin, String destination, Pageable pageable);
    Page<Flight> findAll(Pageable pageable);
}
