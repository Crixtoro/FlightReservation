package com.makaia.flightReservation.web;

import com.makaia.flightReservation.domain.service.FlightCodeGenerator;
import com.makaia.flightReservation.domain.service.FlightService;
import com.makaia.flightReservation.persistence.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
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

    @PostMapping("/flight")
    public ResponseEntity<Flight> save(@RequestBody Flight flight) {

        String code = String.valueOf(flight.getAirline());
        String id = flightCodeGenerator.generateFlightCode(code);

        flight.setCodeFlight(id);
        return new ResponseEntity<>(flightService.save(flight), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") String codeFlight) {
        if(flightService.delete(codeFlight)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /*@GetMapping("/search")
    public Page<Flight> findByCriteria(@RequestParam("origin") String origin,
                                       @RequestParam("destination") String destination,
                                       @RequestParam("departureDate") LocalDateTime departureDate,
                                       Pageable pageable) {
        return flightService.findByCriteria(origin, destination, departureDate, pageable);
    }*/
}
