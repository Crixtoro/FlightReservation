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

        User user = new User("26", "hssdt223", "luis@mail.com");

        when(repository.save(user)).thenReturn(user);

        assertEquals(user,service.save(user));
    }

    @Test
    void delete_a_user() {

        User user = new User( "23sdf", "Karol", "Karol@mail.com");

        when(service.delete(user.getIdUser())).thenReturn(true);

        boolean deleted = service.delete(user.getIdUser());
        assertTrue(deleted);

    }

    @Test
    void search_a_user_by_id() {

        User user = new User("23sfdkie", "Pedro", "pedro@mail.com");
        when(repository.searchById(user.getIdUser())).thenReturn(Optional.of(user));

        Optional<User> optionalUser = service.searchById(user.getIdUser());

        assert optionalUser.isPresent();
        Assertions.assertEquals(optionalUser.get(), user);

    }

    @Test
    void get_list_of_users() {

            when(repository.getAll()).thenReturn(Stream
                    .of(new User("1234", "pablo", "pablo@mail.com"),
                            new User("4321", "Pepita", "pepita@mail.com"))
                    .collect((Collectors.toList())));

            assertEquals(2,service.getAll().size());
    }


    @Test
    void return_true_if_a_user_exists_by_id() {

        User user = new User("7hue6", "Lina", "lina@mail.com");

        when(repository.existsById(user.getIdUser())).thenReturn(true);
        boolean existsId = service.existsById(user.getIdUser());

        assertTrue(existsId);
    }
}