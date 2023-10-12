package com.makaia.flightReservation.domain.service;

import com.makaia.flightReservation.persistence.FlightRepository;
import com.makaia.flightReservation.persistence.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAll() {
        return (List<Flight>) flightRepository.getAll();
    }

    public Optional<Flight> searchFlight(String codeFlight) {
        return flightRepository.searchFlight(codeFlight);
    }

    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }


}
