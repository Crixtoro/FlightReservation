package com.makaia.flightReservation.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "code_reservation",nullable = false)
    private String codeReservation;

    @Column(name = "reservation_date", nullable = false)
    LocalDateTime reservationDate;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @Column(name = "code_flight", nullable = false)
    private String codeFlight;

    @Column(name = "id_passenger", nullable = false)
    private String idPassenger;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "code_flight", insertable = false, updatable = false)
    private Flight flight;

    //---------------------------------------------------------------------
    // Generamos los getter and setter


    public String getCodeReservation() {
        return codeReservation;
    }

    public void setCodeReservation(String codeReservation) {
        this.codeReservation = codeReservation;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCodeFlight() {
        return codeFlight;
    }

    public void setCodeFlight(String codeFlight) {
        this.codeFlight = codeFlight;
    }

    public String getIdPassenger() {
        return idPassenger;
    }

    public void setIdPassenger(String idPassenger) {
        this.idPassenger = idPassenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
