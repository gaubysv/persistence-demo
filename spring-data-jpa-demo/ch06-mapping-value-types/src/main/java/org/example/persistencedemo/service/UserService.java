package org.example.persistencedemo.service;

import org.example.persistencedemo.model.Address;
import org.example.persistencedemo.model.User;
import org.example.persistencedemo.model.UserStatus;
import org.example.persistencedemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void generateUsers() {
        Address bethHomeAddress = new Address("street1", "11111", "city1");
        Address bethBillingAddress = new Address("billingStreet1", "b1111", "billingCity1");
        User beth = new User("beth");
        beth.setStatus(UserStatus.ACTIVE);
        beth.setHomeAddress(bethHomeAddress);
        beth.setBillingAddress(bethBillingAddress);
        beth.setVip(true);

        Address mikeHomeAddress = new Address("street2", "22222", "city2");
        Address mikeBillingAddress = new Address("billingStreet2", "b2222", "billingCity2");
        User mike = new User("mike");
        mike.setStatus(UserStatus.DISABLED);
        mike.setHomeAddress(mikeHomeAddress);
        mike.setBillingAddress(mikeBillingAddress);
        mike.setVip(false);

        userRepository.save(beth);
        userRepository.save(mike);
    }
}
