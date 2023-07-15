package org.example.persistencedemo.onetoone.foreigngenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoApplicationRunner implements ApplicationRunner {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User john = new User();
        john.setUsername("John Smith");

        Address address = new Address();
        address.setStreet("Flowers Street");
        address.setZipcode("01246");
        address.setCity("Boston");
        address.setUser(john);

        john.setShippingAddress(address);
        userRepository.save(john);
    }
}
