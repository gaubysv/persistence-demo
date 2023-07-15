package org.example.persistencedemo.setofstrings;

import org.example.persistencedemo.setofstrings.domain.Item;
import org.example.persistencedemo.setofstrings.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class SetOfStringsApplicationRunner implements ApplicationRunner {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Item item = new Item();
        item.setName("Foo");
        item.getImages().add("background.jpg");
        item.getImages().add("foreground.jpg");
        item.getImages().add("landscape.jpg");
        item.getImages().add("portrait.jpg");

        itemRepository.save(item);

        Item savedItem = itemRepository.findItemWithImages(item.getId());
        List<Item> items = itemRepository.findAll();
        Set<String> images = itemRepository.findImagesNative(item.getId());
    }
}
