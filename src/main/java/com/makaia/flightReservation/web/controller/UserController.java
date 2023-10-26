package com.makaia.flightReservation.web.controller;

import com.makaia.flightReservation.domain.service.UserService;
import com.makaia.flightReservation.persistence.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Users Manager", description = "It allows you to create new users and manage all their information, including specific queries.")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    @Operation(
            summary = "Create a new user",
            description = "Allows creating a new user.", responses = {
            @ApiResponse(responseCode = "201", description = "Created")
    }
    )
    public ResponseEntity<User> save(@RequestBody User user) {
        return new ResponseEntity<User>(userService.save(user),HttpStatus.CREATED);
    }

    @GetMapping("/search/{id}")
    @Operation(
            summary = "Search for a user.",
            description = "Search for a user by their ID.", responses = {
            @ApiResponse(responseCode = "302", description = "Found"),
            @ApiResponse(responseCode = "404", description = "The user was not found.")
    }
    )
    public ResponseEntity<Optional<User>> searchById(@PathVariable("id") Integer idUser) {
        Optional<User> user = userService.searchById(idUser);
        if(user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete a user",
            description = "Allows deleting a user.", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "The user was not found.")
    }
    )
    public ResponseEntity delete(@PathVariable("id") Integer idUser) {
        if(userService.delete(idUser)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search a list of users",
            description = "Allows to get a list of all registered users.", responses = {
            @ApiResponse(responseCode = "302", description = "Found")
    }
    )
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<List<User>>(userService.getAll(),HttpStatus.FOUND);
    }
}
