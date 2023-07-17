package org.example.persistencedemo.onetoone.jointable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoApplicationRunner implements ApplicationRunner {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    public void run(ApplicationArguments args) {
        Shipment shipmentWithoutItem = new Shipment();
        shipmentRepository.save(shipmentWithoutItem);

        Item item = new Item();
        item.setName("Shoes");
        itemRepository.save(item);

        Shipment shipmentWithItem = new Shipment();
        shipmentWithItem.setItem(item);
        shipmentRepository.save(shipmentWithItem);
    }
}
