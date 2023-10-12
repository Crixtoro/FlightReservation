package com.makaia.flightReservation.domain.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FlightCodeGenerator {
        private Map<String, Integer> counterFlightForAirline = new HashMap<>();

        public String generateFlightCode(String airline) {
            String code = airline.substring(0,2);

            Integer counter = counterFlightForAirline.get(airline);
            if (counter == null) {
                counter = 1;
            } else {
                counter++;
            }
            counterFlightForAirline.put(airline, counter);
            return String.format("%s %04d", code, counter);
        }
}
