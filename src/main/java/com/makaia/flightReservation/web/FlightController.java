package com.makaia.flightReservation.web;

import com.makaia.flightReservation.domain.service.FlightService;
import com.makaia.flightReservation.persistence.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAll() {
        return new ResponseEntity<>((List<Flight>) flightService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/search/{idFlight}")
    public ResponseEntity<Optional<Flight>> searchFlight(@PathVariable("idFlight") String codeFlight) {
        Optional<Flight> flight = flightService.searchFlight(codeFlight);
        if(flight.isPresent()) {
            return ResponseEntity.ok(flight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
