package org.example.persistencedemo.onetoone.foreignkey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
