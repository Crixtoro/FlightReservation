package com.makaia.flightReservation.domain.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReservationCodeGenerator {
    private Map<String, Integer> counterReservationCode = new HashMap<>();
    public String generateResevationCode() {
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

}
