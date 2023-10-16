package com.makaia.flightReservation.web;

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

        String codeFlight = reservation.getCodeFlight();
        System.out.println(codeFlight);
        LocalDateTime reservationDate = reservation.getReservationDate();
        System.out.println(reservationDate);

        reservation.setCodeReservation(reservationCodeGenerator.generateResevationCode());
        Reservation reservation1 = reservationService.save(reservation);

        if(reservationValidator.reservationCheck(codeFlight, reservationDate)) {
            System.out.println("Cumple");
            return new ResponseEntity<>(reservation1, HttpStatus.CREATED);
        } else {
            System.out.println("No cumple");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //reservation.setCodeReservation(reservationCodeGenerator.generateResevationCode());

        //Reservation reservation1 = reservationService.save(reservation);




        // Generamos un código único para cada reserva

        //Generamos una reserva

        //Verificamos si el vuelo existe
        //String message = existsByCodeFlight(reservation1.getCodeFlight()) ? "Ok" : "Not found";
        //System.out.println(message);
        //String message1 = reservationService.isSeatAvailable(reservation1.getCodeFlight()) ? "hay sillas" : "No hay silla";

        //System.out.println(reservationService.findNumberOfSeatsByCodeFlight("AV 0001"));

        //LocalDateTime departureDate = reservationService.findDepartureDateByCodeFlight("AV 0001");
        //System.out.println(departureDate);

        //LocalDateTime reservationDate = reservation1.getReservationDate();
        //System.out.println(reservationDate);

        //boolean isAfterThreeHours = departureDate.isAfter(reservationDate.minusHours(3));
        //System.out.println(isAfterThreeHours);

        //Obtenemos código reserva
        //System.out.println(reservation1.getCodeReservation());

        //return new ResponseEntity<>(reservation1, HttpStatus.CREATED);

    }

}
