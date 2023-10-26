package com.makaia.flightReservation.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "code_reservation",nullable = false, length = 10)
    private String codeReservation;

    @Column(name = "reservation_date", nullable = false)
    LocalDateTime reservationDate;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @Column(name = "code_flight", nullable = false, length = 10)
    private String codeFlight;

    @Column(name = "id_user")
    @JsonIgnore
    private Integer idUser;

    @Column(nullable = false, length = 20)
    private String username;

    @ManyToOne
    @JoinColumn(name = "code_flight", insertable = false, updatable = false)
    @JsonIgnore
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

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

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
