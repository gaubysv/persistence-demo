package org.example.persistencedemo;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldJPATest {

    @Test
    void storeLoadMessage() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch02");

        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            Message message = new Message();
            message.setText("Hello World!");

            em.persist(message);

            em.getTransaction().commit();

            em.getTransaction().begin();

            List<Message> messages = em.createQuery("select m from Message m", Message.class).getResultList();

            assertEquals(1, messages.size());
            assertEquals("Hello World!", messages.get(0).getText());

            messages.get(0).setText("Hello World from JPA!");

            em.getTransaction().commit();

            assertEquals("Hello World from JPA!", messages.get(0).getText());

            em.close();
        } finally {
            emf.close();
        }
    }
}