package com.makaia.flightReservation.web.controller;

import com.makaia.flightReservation.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user) {
        return new ResponseEntity<User>(userService.save(user),HttpStatus.CREATED);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Optional<User>> searchById(@PathVariable("id") Integer idUser) {
        Optional<User> user = userService.searchById(idUser);
        if(user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer idUser) {
        if(userService.delete(idUser)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<List<User>>(userService.getAll(),HttpStatus.FOUND);
    }
}
