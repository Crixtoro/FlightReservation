package com.makaia.flightReservation.domain.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReservationCodeGenerator {
    private Map<String, Integer> counterReservationCode = new HashMap<>();
    public String generateReservationCode() {
        String code = "FDGSI";

        Integer counter = counterReservationCode.get(code);
            if (counter == null) {
                counter = 1;
            } else {
                counter ++;
            }
        counterReservationCode.put(code, counter);
        return String.format("%s%02d", code, counter);
    }

    public void deleteReservationCode() {
        // Obtenemos la clave del último elemento
       counterReservationCode.remove(counterReservationCode.keySet().iterator().next());

    }

}
