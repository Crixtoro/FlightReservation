package com.makaia.flightReservation.persistence;

import com.makaia.flightReservation.persistence.crud.FlightCrudRepository;
import com.makaia.flightReservation.persistence.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FlightRepository {
    @Autowired
    private FlightCrudRepository flightCrudRepository; //Implementamos la interfaz que contiene los repositorios

    /*  public List<Flight> getAll() {
        return new ArrayList<>((Collection) flightCrudRepository.findAll());
    }*/

    public List<Flight> getAll() {
        //List<Flight> flights;
        return (List<Flight>) flightCrudRepository.findAll();
    }
}
