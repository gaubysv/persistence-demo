package org.example.persistencedemo;

import org.example.persistencedemo.domain.BankAccount;
import org.example.persistencedemo.domain.BillingDetails;
import org.example.persistencedemo.domain.CreditCard;
import org.example.persistencedemo.repositories.BillingDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TablePerConcreteClassWithUnionsApplication {

    @Autowired
    private BillingDetailsRepository billingDetailsRepository;

    public static void main(String[] args) {
        SpringApplication.run(TablePerConcreteClassWithUnionsApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return env -> {
            CreditCard creditCard = new CreditCard();
            creditCard.setOwner("John Smith");
            creditCard.setCardNumber("123456789");
            creditCard.setExpMonth("10");
            creditCard.setExpYear("2030");
            billingDetailsRepository.save(creditCard);

            BankAccount bankAccount = new BankAccount();
            bankAccount.setOwner("Mike Johnson");
            bankAccount.setAccount("12345");
            bankAccount.setBankName("Delta Bank");
            bankAccount.setSwift("BANKXY12");
            billingDetailsRepository.save(bankAccount);

            List<BillingDetails> billingDetails = billingDetailsRepository.findAll();
        };
    }
}