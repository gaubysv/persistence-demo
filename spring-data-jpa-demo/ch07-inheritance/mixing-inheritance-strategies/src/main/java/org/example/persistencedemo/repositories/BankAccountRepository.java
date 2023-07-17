package org.example.persistencedemo.repositories;

import org.example.persistencedemo.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    List<BankAccount> findBySwift(String swift);
}
