package org.example.persistencedemo;

import org.example.persistencedemo.model.User;
import org.example.persistencedemo.repositories.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Ch04ApplicationTest {

    @Autowired
    protected UserRepository userRepository;

    @BeforeAll
    void beforeAll() {
        userRepository.saveAll(generateUsers());
    }

    @AfterAll
    void afterAll() {
        userRepository.deleteAll();
    }

    private List<User> generateUsers() {
        List<User> users = new ArrayList<>();

        User john = new User("john", LocalDate.of(2020, Month.APRIL, 13));
        john.setEmail("john@somedomain.com");
        john.setLevel(1);
        john.setActive(true);

        User mike = new User("mike", LocalDate.of(2020, Month.FEBRUARY, 2));
        mike.setEmail("mike@somedomain.com");
        mike.setLevel(2);
        mike.setActive(false);

        User beth = new User("beth", LocalDate.of(2021, Month.MARCH, 3));
        beth.setEmail("beth@somedomain.com");
        beth.setLevel(1);
        beth.setActive(true);

        User walter = new User("walter", LocalDate.of(2016, Month.APRIL, 4));
        walter.setEmail("walter@someother1.com");
        walter.setLevel(2);
        walter.setActive(false);

        User tony = new User("tony", LocalDate.of(2018, Month.MAY, 5));
        tony.setEmail("tony@someother.com");
        tony.setLevel(1);
        tony.setActive(true);

        users.add(john);
        users.add(mike);
        users.add(beth);
        users.add(walter);
        users.add(tony);

        return users;
    }
}