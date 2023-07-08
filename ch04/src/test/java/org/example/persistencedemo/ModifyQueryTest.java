package org.example.persistencedemo;

import org.example.persistencedemo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModifyQueryTest extends Ch04ApplicationTest {

    @Test
    void testModifyLevel() {
        int updated = userRepository.updateLevel(1, 3);
        List<User> users = userRepository.findByLevel(3, Sort.by("username"));
        assertAll(
                () -> assertEquals(3, updated),
                () -> assertEquals(3, users.size()),
                () -> assertEquals("beth", users.get(0).getUsername())
        );
    }
}
