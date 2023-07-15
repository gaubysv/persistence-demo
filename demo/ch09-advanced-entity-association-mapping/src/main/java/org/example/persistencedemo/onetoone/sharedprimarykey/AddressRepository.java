package org.example.persistencedemo.onetoone.sharedprimarykey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
