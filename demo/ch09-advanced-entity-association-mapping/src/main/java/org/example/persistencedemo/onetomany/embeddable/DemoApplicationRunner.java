package org.example.persistencedemo.onetomany.embeddable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class DemoApplicationRunner implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        Shipment shipment = new Shipment();
        shipment.setCreatedOn(LocalDateTime.now());

        Address shipmentAddress = new Address();
        shipmentAddress.setCity("London");
        shipmentAddress.setStreet("Green Street");
        shipmentAddress.setZipcode("12345");
        shipmentAddress.getDeliveries().add(shipment);

        User user = new User();
        user.setUsername("John");
        user.setShipmentAddress(shipmentAddress);

        userRepository.save(user);
    }
}
