package com.makaia.flightReservation.web.controller;

import com.makaia.flightReservation.domain.service.ReservationCodeGenerator;
import com.makaia.flightReservation.domain.service.ReservationService;
import com.makaia.flightReservation.domain.service.ReservationValidator;
import com.makaia.flightReservation.persistence.entity.Reservation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Tag(name = "Reservation Manager", description = "It allows you to create new reservations and manage all their information, including specific queries.")
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationCodeGenerator reservationCodeGenerator;

    @Autowired
    private ReservationValidator reservationValidator;

    @GetMapping("/search/{id}")
    @Operation(
            summary = "Check flight availability.",
            description = "Returns true or false if the queried flight exists.", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    }
    )
    public boolean existsByCodeFlight (
            @PathVariable("id") String codeFlight
    ) {
        return reservationService.existsByCodeFlight(codeFlight);
    }

    @GetMapping("{search}")
    @Operation(
            summary = "Reservation list.",
            description = "Obtain a list of all reservations by flight code.", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    }
    )
    public ResponseEntity<List<Reservation>> finByCodeFlight(
            @PathVariable("search") String codeFlight
    ) {
        return ResponseEntity.ok(reservationService.findByCodeFlight(codeFlight));
    }


    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete reservation.",
            description = "Delete a reservation by its reservation code.", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not found")
    }
    )
    public ResponseEntity delete(@PathVariable("id") String codeReservation) {
        if(reservationService.delete(codeReservation)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/code")
    @Operation(
            summary = "Create a reservation.",
            description = "Creating a reservation, provided the following three conditions are met: that the flight exists, " +
                    "there is seat availability, and the reservation is made 3 hours before the flight departure.", responses = {
            @ApiResponse(responseCode = "201", description = "Reservation created"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    }
    )
    public ResponseEntity<Reservation> save(@RequestBody Reservation reservation) {

        reservation.setCodeReservation(reservationCodeGenerator.generateReservationCode());
        Reservation reservation1 = reservationService.save(reservation);
        String codeFlight = reservation.getCodeFlight();
        LocalDateTime reservationDate= reservation1.getReservationDate();

        if(reservationValidator.reservationCheck(codeFlight, reservationDate)) {
            return new ResponseEntity<>(reservationService.save(reservation), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/users/{id}")
    @Operation(
            summary = "List of reservations by user.",
            description = "We query the reservations of a specific user.", responses = {
            @ApiResponse(responseCode = "302", description = "Found")
    }
    )
    public ResponseEntity<List<Reservation>> findByIdUser(Integer idUser) {
        return new ResponseEntity<>(reservationService.findByIdUser(idUser),HttpStatus.FOUND);
    }

}
