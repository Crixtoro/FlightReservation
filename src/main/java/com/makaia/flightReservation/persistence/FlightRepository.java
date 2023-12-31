package com.makaia.flightReservation.persistence;

import com.makaia.flightReservation.persistence.crud.FlightCrudRepository;
import com.makaia.flightReservation.persistence.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FlightRepository {
    @Autowired
    private FlightCrudRepository flightCrudRepository; //Implementamos la interfaz que contiene los repositorios

    //Método para retornar toda la lista de vuelos
    public List<Flight> getAll() {
        return (List<Flight>) flightCrudRepository.findAll();
    }

    //Método para encontrar un vuelo por su id
    public Optional<Flight> searchFlight(String codeFlight) {
        return flightCrudRepository.findById(codeFlight);
    }

    //Método para guardar un vuelo
    public Flight save(Flight flight) {
        return flightCrudRepository.save(flight);
    }

    public void delete(String codeFlight) {
        flightCrudRepository.deleteById(codeFlight);
    }

    public Page<Flight> findByCriteria(String origin, String destination, Pageable pageable) {
        return flightCrudRepository. findByOriginAndDestination
                (origin, destination,pageable);
    }

    public List<Flight> findByStopoverTrue() {
        return flightCrudRepository.findByStopoverTrue();
    }

}
