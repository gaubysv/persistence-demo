package org.example.persistencedemo.onetomany;

import org.example.persistencedemo.onetomany.domain.Bid;
import org.example.persistencedemo.onetomany.domain.Item;
import org.example.persistencedemo.onetomany.repositories.BidRepository;
import org.example.persistencedemo.onetomany.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class OneToManyApplicationRunner implements ApplicationRunner {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BidRepository bidRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Item item = new Item();
        item.setName("Foo");

        Bid bid1 = new Bid();
        bid1.setAmount(BigDecimal.ONE);

        Bid bid2 = new Bid();
        bid2.setAmount(BigDecimal.TEN);

        item.addBid(bid1);
        item.addBid(bid2);

        itemRepository.save(item);
        itemRepository.delete(item);
    }
}
