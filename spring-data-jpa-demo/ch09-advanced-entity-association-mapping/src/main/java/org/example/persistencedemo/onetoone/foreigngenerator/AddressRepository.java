package org.example.persistencedemo.onetoone.foreigngenerator;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
