package com.makaia.flightReservation.persistence.crud;

import com.makaia.flightReservation.persistence.entity.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationCrudRepository extends CrudRepository<Reservation, String> {

    List<Reservation> findByCodeFlight(String codeFlight);
}
