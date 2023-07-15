package org.example.persistencedemo.onetomany.repositories;

import org.example.persistencedemo.onetomany.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Modifying
    @Transactional
    @Query("delete from Item i where i.id = :id")
    void deleteByIdCustom(@Param("id") Long id);
}
