package com.makaia.flightReservation.persistence.crud;

import com.makaia.flightReservation.web.controller.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<User, Integer> {
}
