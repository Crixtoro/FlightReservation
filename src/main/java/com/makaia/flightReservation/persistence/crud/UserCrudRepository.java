package com.makaia.flightReservation.persistence.crud;

import com.makaia.flightReservation.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCrudRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);
}
