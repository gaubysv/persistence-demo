package org.example.persistencedemo.onetomany.repositories;

import org.example.persistencedemo.onetomany.domain.Bid;
import org.example.persistencedemo.onetomany.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BidRepository extends JpaRepository<Bid, Long> {

    Set<Bid> findByItem(Item item);
}
