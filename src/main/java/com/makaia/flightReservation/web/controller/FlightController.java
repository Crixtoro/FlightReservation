package com.makaia.flightReservation.web.controller;

import com.makaia.flightReservation.domain.service.FlightCodeGenerator;
import com.makaia.flightReservation.domain.service.FlightService;
import com.makaia.flightReservation.persistence.entity.Flight;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Flight Manager", description = "It allows you to create new flights and manage all their information, including specific queries.")
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightCodeGenerator flightCodeGenerator;

    @GetMapping("/all")
    @Operation(
            summary = "Query all flights",
            description = "Return a list of available flights.", responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation")
    }
    )
    public ResponseEntity<List<Flight>> getAll() {
        return new ResponseEntity<>((List<Flight>) flightService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Query a flight by its code.",
            description = "Return the information of the found flight.", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found")
    }
    )
    public ResponseEntity<Optional<Flight>> searchFlight(@PathVariable("id") String codeFlight) {
    Optional<Flight> flight = flightService.searchFlight(codeFlight);

        if(flight.isPresent()) {
            return ResponseEntity.ok(flight);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/flight")
    @Operation(
            summary = "Create a new flight.",
            description = "Return the information of the created flight.", responses = {
            @ApiResponse(responseCode = "201", description = "Created"),
    }
    )
    public ResponseEntity<Flight> save(@RequestBody Flight flight) {

        String code = String.valueOf(flight.getAirline());
        String id = flightCodeGenerator.generateFlightCode(code);

        flight.setCodeFlight(id);
        return new ResponseEntity<>(flightService.save(flight), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete a flight.",
            description = "Allows to delete a flight.", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found")
    }
    )
    public ResponseEntity<Void> delete(@PathVariable("id") String codeFlight) {
        if(flightService.delete(codeFlight)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search by criteria.",
            description = "Allows searching for flights by origin and destination.", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found")
    }
    )
    public ResponseEntity <Page<Flight>> searchByCriteria(
            @RequestParam("origin") String origin,
            @RequestParam("destination") String destination,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(flightService.searchByCriteria(origin,destination,pageable),HttpStatus.OK);
    }

    @GetMapping("/search/stopover")
    @Operation(
            summary = "Search by stopovers.",
            description = "Search for flights that have stopovers.", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found")
    }
    )
    public ResponseEntity <List<Flight>> findByStopoverTrue() {
        return new ResponseEntity<List<Flight>>(flightService.findByStopoverTrue(),HttpStatus.OK);
    }
}
