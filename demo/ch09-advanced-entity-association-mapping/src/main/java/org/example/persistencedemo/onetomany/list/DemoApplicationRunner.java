package org.example.persistencedemo.onetomany.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DemoApplicationRunner implements ApplicationRunner {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void run(ApplicationArguments args) {
        Item item = new Item();
        item.setName("Foo");

        Bid bid1 = new Bid();
        bid1.setAmount(BigDecimal.valueOf(2));

        Bid bid2 = new Bid();
        bid2.setAmount(BigDecimal.valueOf(5));

        item.addBid(bid1);
        item.addBid(bid2);
        itemRepository.save(item);
    }
}
