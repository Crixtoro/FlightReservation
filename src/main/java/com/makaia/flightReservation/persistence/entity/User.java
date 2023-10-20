package com.makaia.flightReservation.persistence.entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    public User(Integer idUser, @NotNull String name, @NotNull String email, @NotNull String password) {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.password = password;
    }

//Generamos Getter and Setter

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}