package com.makaia.flightReservation.persistence.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity                     //Con esta anotación indicamos que la clase es una entidad JPA
@Table(name = "flights")     //Especifica el nombre de la tabla de la base de datos que representa la entidad
public class Flight {

    @Id
    @Column(name = "code_flight", nullable = true)
    private String codeFlight;

    @Column(name = "code_reservation", nullable = false)
    private String codeReservation;

    @Column(name = "origin", nullable = false)
    private String origin;

    @NotNull
    private String destination;

    @Column(name = "departure_date", nullable = false)
    private LocalDateTime departureDate;

    @Column(name = "arrival_date", nullable = false)
    private LocalDateTime arrivalDate;

    @NotNull
    private Double price;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @Column(name = "select_flight_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SelectFlightType selectFlightType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Airline airline;

    @NotNull
    private Boolean stopover;

    //-----------------------------------------------------
    // Generamos los Getter and Setter

    public String getCodeFlight() {
        return codeFlight;
    }

    public void setCodeFlight(String codeFlight) {
        this.codeFlight = codeFlight;
    }

    public String getCodeReservation() {
        return codeReservation;
    }

    public void setCodeReservation(String codeReservation) {
        this.codeReservation = codeReservation;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public SelectFlightType getSelectFlightType() {
        return selectFlightType;
    }

    public void setSelectFlightType(SelectFlightType selectFlightType) {
        this.selectFlightType = selectFlightType;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Boolean getStopover() {
        return stopover;
    }

    public void setStopover(Boolean stopover) {
        this.stopover = stopover;
    }
}
