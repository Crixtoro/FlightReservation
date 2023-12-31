package com.makaia.flightReservation.domain.service;

import com.makaia.flightReservation.persistence.ReservationRepository;
import com.makaia.flightReservation.persistence.UserRepository;
import com.makaia.flightReservation.persistence.entity.Reservation;
import com.makaia.flightReservation.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;


    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findByCodeFlight(String codeFlight) {
        return reservationRepository.findByCodeFlight(codeFlight);
    }

    public boolean existsByCodeFlight(String codeFlight) {
        return reservationRepository.existsByCodeFlight(codeFlight);
    }

    public boolean existsById(String codeReservation) {
        return reservationRepository.existsById(codeReservation);
    }

    public boolean delete(String codeReservation){
        if(existsById(codeReservation)) {
            reservationRepository.delete(codeReservation);
            return true;
        } else{
            return false;
        }
    }

    public Integer findNumberOfSeatsByCodeFlight(String codeFlight) {
        return reservationRepository.findNumberOfSeatsByCodeFlight(codeFlight);
    }

    public LocalDateTime findDepartureDateByCodeFlight(String codeFlight) {
        return reservationRepository.findDepartureDateByCodeFlight(codeFlight);
    }

    @Secured("ROLE_ADMIN")
    public List<Reservation> findByIdUser(Integer idUser) {
        return reservationRepository.findByIdUser(idUser);
    }
}
