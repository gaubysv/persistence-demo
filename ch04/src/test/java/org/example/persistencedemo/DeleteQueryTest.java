package org.example.persistencedemo;

import org.example.persistencedemo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteQueryTest extends Ch04ApplicationTest {

    @Test
    void testDeleteByLevel() {
        int deleted = userRepository.deleteByLevel(1);
        List<User> users = userRepository.findByLevel(1, Sort.by("username"));
        assertAll(
                () -> assertEquals(3, deleted),
                () -> assertEquals(0, users.size())
        );
    }

    @Test
    void testDeleteBulkByLevel() {
        int deleted = userRepository.deleteBulkByLevel(2);
        List<User> users = userRepository.findByLevel(2, Sort.by("username"));
        assertAll(
                () -> assertEquals(2, deleted),
                () -> assertEquals(0, users.size())
        );
    }
}
