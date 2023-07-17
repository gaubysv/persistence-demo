package org.example.persistencedemo;

import org.example.persistencedemo.model.User;
import org.example.persistencedemo.repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class Ch04Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch04Application.class, args);
    }

//    @Bean
    public ApplicationRunner configure(UserRepository userRepository) {
        return env -> {
            User user1 = new User("beth", LocalDate.of(2020, Month.AUGUST, 3));
            User user2 = new User("mike", LocalDate.of(2020, Month.JANUARY, 18));

            userRepository.save(user1);
            userRepository.save(user2);

            userRepository.findAll().forEach(System.out::println);
        };
    }
}