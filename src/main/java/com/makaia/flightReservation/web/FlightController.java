package com.makaia.flightReservation.web;

import com.makaia.flightReservation.domain.service.FlightCodeGenerator;
import com.makaia.flightReservation.domain.service.FlightService;
import com.makaia.flightReservation.persistence.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightCodeGenerator flightCodeGenerator;

    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAll() {
        return new ResponseEntity<>((List<Flight>) flightService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Flight>> searchFlight(@PathVariable("id") String codeFlight) {
        Optional<Flight> flight = flightService.searchFlight(codeFlight);
        if(flight.isPresent()) {
            return ResponseEntity.ok(flight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*@PostMapping("/flight")
    public ResponseEntity<Flight> save(@RequestBody Flight flight) {
        return new ResponseEntity<>(flightService.save(flight), HttpStatus.CREATED);
    }*/

    @PostMapping("/flight")
    public ResponseEntity<Flight> save(@RequestBody Flight flight) {

        String code = String.valueOf(flight.getAirline());
        String id = flightCodeGenerator.generateFlightCode(code);

        flight.setCodeFlight(id);
        return new ResponseEntity<>(flightService.save(flight), HttpStatus.CREATED);
    }
}
