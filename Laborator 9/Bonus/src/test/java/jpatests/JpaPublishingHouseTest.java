package jpatests;

import org.example.entities.PublishingHouse;
import org.example.repositories.PublishingHouseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

public class JpaPublishingHouseTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private PublishingHouseRepository publishingHouseRepository;

    @BeforeEach
    void init() {
        emf = Persistence.createEntityManagerFactory("myPersistence");
        em = emf.createEntityManager();
        publishingHouseRepository = new PublishingHouseRepository(PublishingHouse.class);
    }

    @AfterEach
    void cleanup() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    void testSaveAndFind() {
        PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setName("Test Publishing House");
        em.getTransaction().begin();
        publishingHouseRepository.create(publishingHouse);
        em.getTransaction().commit();
        PublishingHouse foundPublishingHouse = publishingHouseRepository.findById(publishingHouse.getId());
        assertNotNull(foundPublishingHouse);
        assertEquals("Test Publishing House", foundPublishingHouse.getName());
    }
}