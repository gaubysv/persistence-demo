package org.example.persistencedemo.onetomany.jointable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DemoApplicationRunner implements ApplicationRunner {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        Item foo = new Item();
        foo.setName("Foo");
        itemRepository.save(foo);

        Item bar = new Item();
        bar.setName("Bar");
        itemRepository.save(bar);

        User john = new User();
        john.setUsername("John");
        john.addBoughtItem(foo);
        john.addBoughtItem(bar);
        userRepository.save(john);
    }
}
