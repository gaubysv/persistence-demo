package org.example.persistencedemo.repositories;

import org.example.persistencedemo.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
