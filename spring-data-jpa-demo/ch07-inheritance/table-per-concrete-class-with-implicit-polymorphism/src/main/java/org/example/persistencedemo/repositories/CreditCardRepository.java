package org.example.persistencedemo.repositories;

import org.example.persistencedemo.domain.CreditCard;

import java.util.List;

public interface CreditCardRepository extends BillingDetailsRepository<CreditCard, Long> {

    List<CreditCard> findByExpYear(String expYear);
}
