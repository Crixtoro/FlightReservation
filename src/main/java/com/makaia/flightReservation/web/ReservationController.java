package com.makaia.flightReservation.web;

import com.makaia.flightReservation.domain.service.ReservationService;
import com.makaia.flightReservation.persistence.ReservationRepository;
import com.makaia.flightReservation.persistence.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/code")
    public ResponseEntity<Reservation> save(@RequestBody Reservation reservation) {
        Reservation reservation1 = reservationService.save(reservation);
        System.out.println(reservation1.getCodeReservation());
        return new ResponseEntity<>(reservation1, HttpStatus.CREATED);

    }

    @GetMapping("{search}")
    public ResponseEntity<List<Reservation>> finByCodeFlight(
            @PathVariable("search") String codeFlight
    ) {
        return ResponseEntity.ok(reservationService.findByCodeFlight(codeFlight));
    }
}
