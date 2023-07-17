package org.example.persistencedemo.part1;

import org.hibernate.LazyInitializationException;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ch10ManagingDataPart1Test {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10-part1");

    private static final EntityManagerFactory emfReplica = Persistence.createEntityManagerFactory("ch10-part1-replica");

    @Test
    void makePersistence() {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Item item = new Item();
        item.setName("Some item");
        em.persist(item);

        Long itemId = item.getId();

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        assertEquals("Some item", em.find(Item.class, itemId).getName());
        em.getTransaction().commit();
        em.close();
    }

    @Test
    void retrievePersistent() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Item someItem = new Item();
        someItem.setName("Some item (2)");
        em.persist(someItem);
        em.getTransaction().commit();
        em.close();

        Long itemId = someItem.getId();

        {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Item item = em.find(Item.class, itemId);
            if (item != null) {
                item.setName("New name (2)");
            }
            em.getTransaction().commit();
            em.close();
        }

        {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Item item1 = em.find(Item.class, itemId);
            Item item2 = em.find(Item.class, itemId);
            assertTrue(item1 == item2);
            em.getTransaction().commit();
            em.close();
        }
    }

    @Test
//    @Disabled("For some reason em.getReference(...) still executes a select SQL query immediately...")
    // Okay so it turns out that the problem was caused by debug mode, the debugger accesses the properties of the proxy
    // in order to show them and this action triggers the SQL select statement execution. In other words this test
    // fails in debug mode but passes in a non debug mode.
    void retrievePersistentReference() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Item someItem = new Item();
        someItem.setName("Some item (3)");
        em.persist(someItem);
        em.getTransaction().commit();
        em.close();

        Long itemId = someItem.getId();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        Item item = em.getReference(Item.class, itemId);

        PersistenceUnitUtil persistenceUnitUtil = emf.getPersistenceUnitUtil();
        assertFalse(persistenceUnitUtil.isLoaded(item));

//        assertEquals(itemId, item.getId());
//        assertEquals("Some item (3)", item.getName());

//        Hibernate.initialize(item);

        em.getTransaction().commit();
        em.close();

        assertThrows(LazyInitializationException.class, () -> item.getName());
    }

    @Test
    void makeTransient() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Item someItem = new Item();
        someItem.setName("Some item (4)");
        em.persist(someItem);
        em.getTransaction().commit();
        em.close();

        Long itemId = someItem.getId();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        Item item = em.find(Item.class, itemId);
//        Item item = em.getReference(Item.class, itemId);

        em.remove(item);

        assertFalse(em.contains(item));
        assertNull(item.getId());

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        item = em.find(Item.class, itemId);
        assertNull(item);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    void refresh() throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Item someItem = new Item();
        someItem.setName("Some item");
        em.persist(someItem);
        em.getTransaction().commit();
        em.close();

        Long itemId = someItem.getId();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        Item item = em.find(Item.class, itemId);
        item.setName("Some item updated");

        // Someone updates this row in the database
        Executors.newSingleThreadExecutor().submit(() -> {
            EntityManager em1 = emf.createEntityManager();
            try {
                em1.getTransaction().begin();

                Session session = em1.unwrap(Session.class);
                session.doWork(con -> {
                    Item item1 = em1.find(Item.class, itemId);
                    item1.setName("Some item updated concurrently");
                    em1.persist(item1);
                });

                em1.getTransaction().commit();
                em1.close();
            } catch (Exception ex) {
                throw new RuntimeException("Concurrent operation failure: " + ex, ex);
            }
            return null;
        }).get();

        em.refresh(item);
        em.getTransaction().commit();

        em.refresh(item);
        em.close();

        assertEquals("Some item updated concurrently", item.getName());
    }

    @Test
    void replicate() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Item someItem = new Item();
        someItem.setName("Some item");
        em.persist(someItem);
        em.getTransaction().commit();
        em.close();

        Long itemId = someItem.getId();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        Item item = em.find(Item.class, itemId);
        em.getTransaction().commit();

        EntityManager emReplica = emfReplica.createEntityManager();
        emReplica.getTransaction().begin();
        Session session = emReplica.unwrap(Session.class);
        session.replicate(item, ReplicationMode.LATEST_VERSION);
        Item itemReplicated = emReplica.find(Item.class, itemId);
        assertEquals("Some item", itemReplicated.getName());
        emReplica.getTransaction().commit();

        em.close();
        emReplica.close();
    }

    @Test
    void flushModeType() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Item someItem = new Item();
        someItem.setName("Original Name");
        em.persist(someItem);
        em.getTransaction().commit();
        em.close();

        Long itemId = someItem.getId();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        Item item = em.find(Item.class, itemId);
        item.setName("New name");

        em.setFlushMode(FlushModeType.COMMIT);

        String itemName = em.createQuery("select i.name from Item i where i.id = :id", String.class)
                .setParameter("id", itemId)
                .getSingleResult();

        assertEquals("Original Name", itemName);

        em.getTransaction().commit();
        em.close();
    }

    @Test
    void scopeOfIdentity() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Item someItem = new Item();
        someItem.setName("Original Name");
        em.persist(someItem);
        em.getTransaction().commit();
        em.close();

        Long itemId = someItem.getId();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        Item a = em.find(Item.class, itemId);
        Item b = em.find(Item.class, itemId);
        Item c = em.createQuery("select i from Item i where i.id = :id", Item.class)
                .setParameter("id", itemId)
                .getSingleResult();

        assertTrue(a == b);
        assertTrue(a.equals(b));
        assertEquals(a.getId(), b.getId());

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        Item d = em.find(Item.class, itemId);
        assertTrue(a != d);
        assertFalse(a.equals(d));
        assertEquals(a.getId(), d.getId());

        em.getTransaction().commit();
        em.close();

        Set<Item> allItems = new HashSet<>();
        allItems.add(a);
        allItems.add(b);
        allItems.add(c);
        allItems.add(d);
        assertEquals(2, allItems.size());
    }

    @Test
    void detach() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Item someItem = new Item();
        someItem.setName("Original Name");
        em.persist(someItem);
        em.getTransaction().commit();
        em.close();

        Long itemId = someItem.getId();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        Item item = em.find(Item.class, itemId);
        assertTrue(em.contains(item));
        em.detach(item);
        assertFalse(em.contains(item));

        em.getTransaction().commit();
        em.close();
    }

    @Test
    void mergeDetached() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Item someItem = new Item();
        someItem.setName("Original Name");
        em.persist(someItem);
        em.getTransaction().commit();
        em.close();

        Item detatchedItem = someItem;
        detatchedItem.setName("New name");
        Long itemId = someItem.getId();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        Item mergedItem = em.merge(detatchedItem);
        mergedItem.setName("New name 2");
        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();
        Item item = em.find(Item.class, itemId);
        assertEquals("New name 2", item.getName());
        em.getTransaction().commit();
        em.close();
    }
}
