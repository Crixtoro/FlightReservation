package com.makaia.flightReservation.domain.service;

import com.makaia.flightReservation.persistence.UserRepository;
import com.makaia.flightReservation.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    //Borramos un usuario por su id
    public boolean delete(int idUser) {
        if(existsById(idUser)) {
            userRepository.delete(idUser);
            return true;
        } else return false;
    }

    //Buscamos un usuario por su id
    public Optional<User> searchById(int idUser) {
        return userRepository.searchById(idUser);
    }

    //Consultamos todas los usuarios
    public List<User> getAll() {
        return (List<User>) userRepository.getAll();
    }

    public boolean existsById(int idUser){
        return userRepository.existsById(idUser);
    }

}
