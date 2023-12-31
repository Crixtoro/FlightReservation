package com.makaia.flightReservation.persistence;

import com.makaia.flightReservation.persistence.crud.UserCrudRepository;
import com.makaia.flightReservation.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    private UserCrudRepository userCrudRepository;

    //Creamos un usuario
    public User save(User user) {
        return userCrudRepository.save(user);
    }

    //Borramos un usuario por su id
    public void delete(Integer idUser) {
        userCrudRepository.deleteById(idUser);
    }

    //Buscamos un usuario por su id
    public Optional<User> searchById(Integer idUser) {
        return userCrudRepository.findById(idUser);
    }

    //Consultamos todas los usuarios
    public List<User> getAll() {
        return (List<User>) userCrudRepository.findAll();
    }

    public boolean existsById(Integer idUser){
        return userCrudRepository.existsById(idUser);
    }

    public User findByUsername(String username) {
        return userCrudRepository.findByUsername(username);
    }

}
