package com.makaia.flightReservation.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity                      //Con esta anotación indicamos que la clase es una entidad JPA
@Table(name = "flights")     //Especifica el nombre de la tabla de la base de datos que representa la entidad
public class Flight {

    @Id
    @Column(name = "code_flight", nullable = true, length = 10)
    private String codeFlight;

    @Column(name = "origin", nullable = false, length = 20)
    private String origin;

    @Column(nullable = false, length = 20)
    private String destination;

    @Column(name = "departure_date", nullable = false, length = 20)
    private LocalDateTime departureDate;

    @Column(name = "arrival_date", nullable = false, length = 20)
    private LocalDateTime arrivalDate;

    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    private Double price;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @Column(name = "select_flight_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SelectFlightType selectFlightType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Airline airline;

    @Column(nullable = false)
    private Boolean stopover;

    // Generamos las relaciones entre tablas
    @OneToMany(mappedBy = "flight")
    @JsonIgnore
    private List<Reservation> reservationList;

    public String getCodeFlight() {
        return codeFlight;
    }

    public void setCodeFlight(String codeFlight) {
        this.codeFlight = codeFlight;
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

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
