package com.makaia.flightReservation.domain.service;

import com.makaia.flightReservation.persistence.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationValidator {

    @Autowired
    private ReservationService reservationService;


    public boolean reservationCheck(Reservation reservation) {
        String codeFlight = reservation.getCodeFlight();
        System.out.println(codeFlight);
        if(reservationService.existsByCodeFlight(codeFlight)) {
            System.out.println("Entro");
            LocalDateTime departureDate = reservationService.findDepartureDateByCodeFlight(codeFlight);

            if (departureDate.isAfter(reservation.getReservationDate().plusHours(3))) {
                //validamos si hay sillas disponibles
                Integer flightSeats = reservationService.findNumberOfSeatsByCodeFlight(codeFlight);
                List<Reservation> reservationList = reservationService.findByCodeFlight(codeFlight);

                return (flightSeats - (reservationList.size()) > 0);

            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    /*public boolean reservationCheck(String codeFlight, LocalDateTime reservationDate) {
        if(reservationService.existsByCodeFlight(codeFlight)) {
            System.out.println("Entro");
            LocalDateTime departureDate = reservationService.findDepartureDateByCodeFlight(codeFlight);

            if (departureDate.isAfter(reservationDate.plusHours(3))) {
                //validamos si hay sillas disponibles
                Integer flightSeats = reservationService.findNumberOfSeatsByCodeFlight(codeFlight);
                List<Reservation> reservationList = reservationService.findByCodeFlight(codeFlight);

                return (flightSeats - (reservationList.size()) > 0);

            } else {
                return false;
            }
        } else {
            return false;
        }
    }*/
}
