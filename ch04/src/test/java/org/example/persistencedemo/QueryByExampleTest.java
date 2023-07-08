package org.example.persistencedemo;

import org.example.persistencedemo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryByExampleTest extends Ch04ApplicationTest {

    @Test
    void testEmailWithQueryByExample() {
        User user = new User();
        user.setEmail("@somedomain.com");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("level", "active")
                .withMatcher("email", match -> match.endsWith());
        Example<User> example = Example.of(user, matcher);
        List<User> users = userRepository.findAll(example);
        assertEquals(3, users.size());
    }

    @Test
    void testUsernameWithQueryByExample() {
        User user = new User();
        user.setUsername("BE");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("level", "active")
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
                .withIgnoreCase();
        Example<User> example = Example.of(user, matcher);
        List<User> users = userRepository.findAll(example);
        assertEquals(1, users.size());
    }
}
