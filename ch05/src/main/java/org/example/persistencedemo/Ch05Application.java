package org.example.persistencedemo;

import org.example.persistencedemo.service.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ch05Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch05Application.class, args);
    }

    @Bean
    public ApplicationRunner configure(UserService userService) {
        return env -> {
            userService.generateUsers();
        };
    }
}