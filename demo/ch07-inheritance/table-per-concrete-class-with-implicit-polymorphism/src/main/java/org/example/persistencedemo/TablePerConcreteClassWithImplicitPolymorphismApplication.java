package org.example.persistencedemo;

import org.example.persistencedemo.domain.BankAccount;
import org.example.persistencedemo.domain.CreditCard;
import org.example.persistencedemo.repositories.BankAccountRepository;
import org.example.persistencedemo.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TablePerConcreteClassWithImplicitPolymorphismApplication {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    public static void main(String[] args) {
        SpringApplication.run(TablePerConcreteClassWithImplicitPolymorphismApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return env -> {
            CreditCard creditCard = new CreditCard();
            creditCard.setOwner("John Smith");
            creditCard.setCardNumber("123456789");
            creditCard.setExpMonth("10");
            creditCard.setExpYear("2030");
            creditCardRepository.save(creditCard);

            BankAccount bankAccount = new BankAccount();
            bankAccount.setOwner("Mike Johnson");
            bankAccount.setAccount("12345");
            bankAccount.setBankName("Delta Bank");
            bankAccount.setSwift("BANKXY12");
            bankAccountRepository.save(bankAccount);

            List<CreditCard> creditCards = creditCardRepository.findByOwner("John Smith");
            List<BankAccount> bankAccounts = bankAccountRepository.findByOwner("Mike Johnson");
            List<CreditCard> creditCards2 = creditCardRepository.findByExpYear("2030");
            List<BankAccount> bankAccounts2 = bankAccountRepository.findBySwift("BANKXY12");
        };
    }
}