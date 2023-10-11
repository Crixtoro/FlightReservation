package com.makaia.flightReservation.domain.service;

import com.makaia.flightReservation.persistence.FlightRepository;
import com.makaia.flightReservation.persistence.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAll() {
        return (List<Flight>) flightRepository.getAll();
    }

   /* public Optional<Iterable<Flight>> findById(String codeFlight) {
        return Optional.of(flightCrudRepository.findAllById(List.of(codeFlight)));
    }*/
}
