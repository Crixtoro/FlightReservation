package com.makaia.flightReservation.persistence.crud;

import com.makaia.flightReservation.persistence.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;

public interface ReservationCrudRepository extends JpaRepository<Reservation, String> {

    List<Reservation> findByCodeFlight(String codeFlight);
    boolean existsByCodeFlight(String codeFlight);
    @Query(value = "SELECT flights.available_seats FROM reservation INNER JOIN flights ON reservation.code_flight = flights.code_flight WHERE reservation.code_flight = :codeFlight", nativeQuery = true)
    Integer findNumberOfSeatsByCodeFlight(@Param("codeFlight") String codeFlight);
    @Query(value = "SELECT flights.departure_date FROM reservation INNER JOIN flights ON reservation.code_flight = flights.code_flight WHERE reservation.code_flight = :codeFlight LIMIT 1", nativeQuery = true)
    LocalDateTime findDepartureDateByCodeFlight(@Param("codeFlight") String codeFlight);
    List<Reservation> findByIdUser(Integer idUser);
}
