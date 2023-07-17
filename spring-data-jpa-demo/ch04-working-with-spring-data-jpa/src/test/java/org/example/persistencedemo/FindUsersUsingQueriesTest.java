package org.example.persistencedemo;

import org.example.persistencedemo.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindUsersUsingQueriesTest extends Ch04ApplicationTest {

    @Test
    void testFindAll() {
        List<User> users = userRepository.findAll();
        assertEquals(5, users.size());
    }
    @Test
    void testFindUser() {
        User beth = userRepository.findByUsername("beth");
        assertEquals("beth", beth.getUsername());
    }
    @Test
    void testFindAllByOrderByUsernameAsc() {
        List<User> users = userRepository.findAllByOrderByUsernameAsc();
        assertAll(() -> assertEquals(5, users.size()),
                () -> assertEquals("beth", users.get(0).getUsername()),
                () -> assertEquals("walter",
                        users.get(users.size() - 1).getUsername()));
    }
    @Test
    void testFindByRegistrationDateBetween() {
        List<User> users = userRepository.findByRegistrationDateBetween(
                LocalDate.of(2020, Month.JANUARY, 1),
                LocalDate.of(2020, Month.DECEMBER, 31));
        assertEquals(2, users.size());
    }
}
