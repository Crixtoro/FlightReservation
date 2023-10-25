package com.makaia.flightReservation.web.controller;

import com.makaia.flightReservation.domain.service.ReservationCodeGenerator;
import com.makaia.flightReservation.domain.service.ReservationService;
import com.makaia.flightReservation.domain.service.ReservationValidator;
import com.makaia.flightReservation.persistence.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationCodeGenerator reservationCodeGenerator;

    @Autowired
    private ReservationValidator reservationValidator;

    //Consultamos si el vuelo de la reserva existe
    @GetMapping("/search/{id}")
    public boolean existsByCodeFlight (
            @PathVariable("id") String codeFlight
    ) {
        return reservationService.existsByCodeFlight(codeFlight);
    }

    //Traemos todas las reservas por vuelo
    @GetMapping("{search}")
    public ResponseEntity<List<Reservation>> finByCodeFlight(
            @PathVariable("search") String codeFlight
    ) {
        return ResponseEntity.ok(reservationService.findByCodeFlight(codeFlight));
    }

    //Borramos una reserva
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") String codeReservation) {
        if(reservationService.delete(codeReservation)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Creamos una reserva, pero debemos validar cada una de las condiciones primero
    @PostMapping("/code")
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
    public ResponseEntity<List<Reservation>> findByIdUser(Integer idUser) {
        return new ResponseEntity<>(reservationService.findByIdUser(idUser),HttpStatus.FOUND);
    }

}
