package org.example.persistencedemo;

import org.example.persistencedemo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindUsersSortingAndPagingTest extends Ch04ApplicationTest {

    @Test
    void testOrder() {
        User user1 = userRepository.findFirstByOrderByUsernameAsc();
        User user2 = userRepository.findTopByOrderByRegistrationDateDesc();
        Page<User> userPage = userRepository.findAll(PageRequest.of(1, 3));
        List<User> users = userRepository.findFirst2ByLevel(2,
                Sort.by("registrationDate"));
        assertAll(
                () -> assertEquals("beth", user1.getUsername()),
                () -> assertEquals("beth", user2.getUsername()),
                () -> assertEquals(2, users.size()),
                () -> assertEquals(3, userPage.getSize()),
                () -> assertEquals("walter", users.get(0).getUsername()),
                () -> assertEquals("mike", users.get(1).getUsername())
        );
    }
    @Test
    void testFindByLevel() {
        Sort.TypedSort<User> user = Sort.sort(User.class);
        List<User> users = userRepository.findByLevel(1,
                user.by(User::getRegistrationDate).descending());
        assertAll(
                () -> assertEquals(3, users.size()),
                () -> assertEquals("beth", users.get(0).getUsername())
        );
    }
}
