package com.makaia.flightReservation.persistence;

import com.makaia.flightReservation.persistence.crud.FlightCrudRepository;
import com.makaia.flightReservation.persistence.crud.ReservationCrudRepository;
import com.makaia.flightReservation.persistence.entity.Flight;
import com.makaia.flightReservation.persistence.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public boolean existsByCodeFlight(String codeFlight) {
        return reservationCrudRepository.existsByCodeFlight(codeFlight);
    }

    public Reservation save(Reservation reservation) {
        return reservationCrudRepository.save(reservation);
    }

    public List<Reservation> findByCodeFlight(String codeFlight) {
        return reservationCrudRepository.findByCodeFlight(codeFlight);
    }

    public void delete(String codeReservation) {
        reservationCrudRepository.deleteById(codeReservation);
    }

    public boolean existsById(String codeReservation) {
        return reservationCrudRepository.existsById(codeReservation);
    }

    public Integer findNumberOfSeatsByCodeFlight(String codeFlight) {
        return reservationCrudRepository.findNumberOfSeatsByCodeFlight(codeFlight);
    }

    public LocalDateTime findDepartureDateByCodeFlight(String codeFlight) {
        return reservationCrudRepository.findDepartureDateByCodeFlight(codeFlight);
    }

}
