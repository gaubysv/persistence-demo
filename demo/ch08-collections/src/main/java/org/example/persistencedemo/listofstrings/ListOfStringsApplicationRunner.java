package org.example.persistencedemo.listofstrings;

import org.example.persistencedemo.listofstrings.domain.Item;
import org.example.persistencedemo.listofstrings.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ListOfStringsApplicationRunner implements ApplicationRunner {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void run(ApplicationArguments args) {
        Item item1 = new Item();
        item1.setName("Foo");
        item1.getImages().add("background.jpg");
        item1.getImages().add("landscape.jpg");
        item1.getImages().add("foreground.jpg");
        item1.getImages().add("background.jpg");

        Item item2 = new Item();
        item2.setName("Bar");
        item2.getImages().add("portrait.jpg");
        item2.getImages().add("foreground.jpg");

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> items = itemRepository.findAll();

        Item savedItem1 = itemRepository.findItemWithImages(item1.getId());
        List<String> images1 = itemRepository.findImagesNative(item1.getId());

        Item savedItem2 = itemRepository.findItemWithImages(item2.getId());
        List<String> images2 = itemRepository.findImagesNative(item2.getId());
    }
}
