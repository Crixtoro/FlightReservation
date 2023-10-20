package com.makaia.flightReservation.domain.service;

import com.makaia.flightReservation.persistence.UserRepository;
import com.makaia.flightReservation.persistence.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService service;
    @MockBean
    private UserRepository repository;

    @Test
    void create_a_new_user() {

        User user = new User(26, "Luis", "luis@mail.com", "ewe23");

        when(repository.save(user)).thenReturn(user);

        assertEquals(user,service.save(user));
    }

    @Test
    void delete_a_user() {

        User user = new User(321, "Karol", "Karol@mail.com", "23sdf");

        when(service.delete(user.getIdUser())).thenReturn(true);

        boolean deleted = service.delete(user.getIdUser());
        assertTrue(deleted);

    }

    @Test
    void search_a_user_by_id() {

        User user = new User(23, "Pedro", "pedro@mail.com", "23sfdkie");
        when(repository.searchById(user.getIdUser())).thenReturn(Optional.of(user));

        Optional<User> optionalUser = service.searchById(user.getIdUser());

        assert optionalUser.isPresent();
        Assertions.assertEquals(optionalUser.get(), user);

    }

    @Test
    void get_list_of_users() {

            when(repository.getAll()).thenReturn(Stream
                    .of(new User(1, "pablo", "pablo@mail.com", "1234"),
                            new User(2, "Pepita", "pepita@mail.com", "4321"))
                    .collect((Collectors.toList())));

            assertEquals(2,service.getAll().size());
    }


    @Test
    void return_true_if_a_user_exists_by_id() {

        User user = new User(242, "Lina", "lina@mail.com", "7hue6");

        when(repository.existsById(user.getIdUser())).thenReturn(true);
        boolean existsId = service.existsById(user.getIdUser());

        assertTrue(existsId);
    }
}