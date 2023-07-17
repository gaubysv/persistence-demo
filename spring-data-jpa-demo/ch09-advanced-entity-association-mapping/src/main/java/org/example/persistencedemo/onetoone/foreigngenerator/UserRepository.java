package org.example.persistencedemo.onetoone.foreigngenerator;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
