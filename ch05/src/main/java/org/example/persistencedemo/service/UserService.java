package org.example.persistencedemo.service;

import org.example.persistencedemo.model.User;
import org.example.persistencedemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void generateUsers() {
        User beth = new User("beth", LocalDate.of(2020, Month.AUGUST, 3));
        User mike = new User("mike", LocalDate.of(2020, Month.JANUARY, 18));
        User walter = new User("walter", LocalDate.of(2020, Month.MARCH, 21));
        User john = new User("john", LocalDate.of(2020, Month.APRIL, 13));
        User tony = new User("tony", LocalDate.of(2018, Month.MAY, 5));

        userRepository.save(beth);
        userRepository.save(mike);
        userRepository.save(walter);
        userRepository.save(john);
        userRepository.save(tony);
    }
}
