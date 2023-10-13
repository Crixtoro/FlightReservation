package com.makaia.flightReservation.domain.service;

import com.makaia.flightReservation.persistence.ReservationRepository;
import com.makaia.flightReservation.persistence.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findByCodeFlight(String codeFlight) {
        return reservationRepository.findByCodeFlight(codeFlight);
    }
}
