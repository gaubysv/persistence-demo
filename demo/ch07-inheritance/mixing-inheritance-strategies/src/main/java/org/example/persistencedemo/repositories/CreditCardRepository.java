package org.example.persistencedemo.repositories;

import org.example.persistencedemo.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    List<CreditCard> findByExpYear(String expYear);
}
