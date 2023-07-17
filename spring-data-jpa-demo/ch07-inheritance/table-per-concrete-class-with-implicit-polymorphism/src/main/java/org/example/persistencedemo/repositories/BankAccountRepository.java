package org.example.persistencedemo.repositories;

import org.example.persistencedemo.domain.BankAccount;

import java.util.List;

public interface BankAccountRepository extends BillingDetailsRepository<BankAccount, Long> {

    List<BankAccount> findBySwift(String swift);
}
