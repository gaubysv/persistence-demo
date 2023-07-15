package org.example.persistencedemo.mapofstrings;

import org.example.persistencedemo.mapofstrings.domain.Item;
import org.example.persistencedemo.mapofstrings.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class MapOfStringsApplicationRunner implements ApplicationRunner {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void run(ApplicationArguments args) {
        Item item = new Item();
        item.setName("Foo");
        item.getImages().put("landscape.jpg", "Landscape");
        item.getImages().put("foreground.jpg", "Foreground");
        item.getImages().put("background.jpg", "Background");
        item.getImages().put("portrait.jpg", "Portrait");

        itemRepository.save(item);

        Item savedItem = itemRepository.findItemWithImages(item.getId());
        List<Item> items = itemRepository.findAll();
        Collection<String> images = itemRepository.findImagesNative(item.getId());
    }
}
