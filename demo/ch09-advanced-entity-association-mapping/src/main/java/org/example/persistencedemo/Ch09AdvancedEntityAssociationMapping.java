package org.example.persistencedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = Ch09AdvancedEntityAssociationMapping.BASE_PACKAGE)
@EnableJpaRepositories(basePackages = Ch09AdvancedEntityAssociationMapping.BASE_PACKAGE)
@SpringBootApplication(scanBasePackages = Ch09AdvancedEntityAssociationMapping.BASE_PACKAGE)
public class Ch09AdvancedEntityAssociationMapping {

    public static final String BASE_PACKAGE = "org.example.persistencedemo.onetomany.embeddable";

    public static void main(String[] args) {
        SpringApplication.run(Ch09AdvancedEntityAssociationMapping.class, args);
    }
}