package com.makaia.flightReservation.persistence.crud;

import com.makaia.flightReservation.persistence.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;


public interface FlightCrudRepository extends PagingAndSortingRepository<Flight, String> {

    //Page<Flight> findByOriginAndDestinationAndDepartureDate(String origin, String destination, LocalDateTime departureDate, Pageable pageable);


}
