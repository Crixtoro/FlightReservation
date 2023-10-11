package com.makaia.flightReservation.persistence.crud;

import com.makaia.flightReservation.persistence.entity.Flight;
import org.springframework.data.repository.CrudRepository;


public interface FlightCrudRepository extends CrudRepository<Flight, String> {

}
