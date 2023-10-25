package com.makaia.flightReservation.persistence.crud;

import com.makaia.flightReservation.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<User, Integer> {

    public User findByUsername(String username);
}
