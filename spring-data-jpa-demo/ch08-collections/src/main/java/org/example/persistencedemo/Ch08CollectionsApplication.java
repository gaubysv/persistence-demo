package org.example.persistencedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = Ch08CollectionsApplication.BASE_PACKAGE)
@EnableJpaRepositories(basePackages = Ch08CollectionsApplication.BASE_PACKAGE)
@SpringBootApplication(scanBasePackages = Ch08CollectionsApplication.BASE_PACKAGE)
public class Ch08CollectionsApplication {

    public static final String BASE_PACKAGE = "org.example.persistencedemo.onetomany";

    public static void main(String[] args) {
        SpringApplication.run(Ch08CollectionsApplication.class, args);
    }
}