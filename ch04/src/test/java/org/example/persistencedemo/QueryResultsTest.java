package org.example.persistencedemo;

import org.example.persistencedemo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryResultsTest extends Ch04ApplicationTest {

    @Test
    void testStreamable() {
        try (Stream<User> result =
                     userRepository.findByEmailContaining("someother")
                             .and(userRepository.findByLevel(2))
                             .stream().distinct()) {
            assertEquals(3, result.count());
        }
    }

    @Test
    void testFindByAsArrayAndSort() {
        List<Object[]> usersList1 =
                userRepository.findByAsArrayAndSort("t", Sort.by("username"));
        List<Object[]> usersList2 =
                userRepository.findByAsArrayAndSort("t",
                        Sort.by("email_length").descending());
        List<Object[]> usersList3 = userRepository.findByAsArrayAndSort(
                "t", JpaSort.unsafe("LENGTH(u.email)"));
        assertAll(
                () -> assertEquals(3, usersList1.size()),
                () -> assertEquals("beth", usersList1.get(0)[0]),
                () -> assertEquals(19, usersList1.get(0)[1]),
                () -> assertEquals(3, usersList2.size()),
                () -> assertEquals("walter", usersList2.get(0)[0]),
                () -> assertEquals(21, usersList2.get(0)[1]),
                () -> assertEquals(3, usersList3.size()),
                () -> assertEquals("tony", usersList3.get(0)[0]),
                () -> assertEquals(18, usersList3.get(0)[1])
        );
    }
}
