package com.makaia.flightReservation.persistence;

import com.makaia.flightReservation.persistence.crud.ReservationCrudRepository;
import com.makaia.flightReservation.persistence.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public Reservation save(Reservation reservation) {
        return reservationCrudRepository.save(reservation);
    }

    public List<Reservation> findByCodeFlight(String codeFlight) {
        return reservationCrudRepository.findByCodeFlight(codeFlight);
    }

}
