package org.example.persistencedemo;

import org.example.persistencedemo.model.Projections;
import org.example.persistencedemo.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectionsTest extends Ch04ApplicationTest {

    @Test
    void testProjectionUsername() {
        List<Projections.UsernameOnly> users = userRepository.findByEmail("john@somedomain.com");

        assertAll(
                () -> assertEquals(1, users.size()),
                () -> assertEquals("john", users.get(0).getUsername())
        );
    }

    @Test
    void testProjectionUserSummary() {
        List<Projections.UserSummary> users =
                userRepository.findByRegistrationDateAfter(
                        LocalDate.of(2021, Month.FEBRUARY, 1));

        assertAll(
                () -> assertEquals(1, users.size()),
                () -> assertEquals("beth", users.get(0).getUsername()),
                () -> assertEquals("beth beth@somedomain.com",
                        users.get(0).getInfo())
        );
    }

    @Test
    void testDynamicProjection() {
        List<Projections.UsernameOnly> usernames =
                userRepository.findByEmail("mike@somedomain.com",
                        Projections.UsernameOnly.class);

        List<Projections.UserSummary> userSummaries =
                userRepository.findByEmail("mike@somedomain.com",
                        Projections.UserSummary.class);

        List<User> users = userRepository.findByEmail("mike@somedomain.com", User.class);

        assertAll(
                () -> assertEquals(1, usernames.size()),
                () -> assertEquals("mike", usernames.get(0).getUsername()),
                () -> assertEquals(1, userSummaries.size()),
                () -> assertEquals("mike", userSummaries.get(0).getUsername()),
                () -> assertEquals("mike mike@somedomain.com", userSummaries.get(0).getInfo()),
                () -> assertEquals(1, users.size()),
                () -> assertEquals("mike", users.get(0).getUsername())
        );
    }
}
