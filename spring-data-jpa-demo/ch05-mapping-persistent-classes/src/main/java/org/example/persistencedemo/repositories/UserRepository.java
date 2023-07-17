package org.example.persistencedemo.repositories;

import org.example.persistencedemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
