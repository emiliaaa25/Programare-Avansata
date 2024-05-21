package jpatests;

import org.example.entities.Book;
import org.example.repositories.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

public class JpaBookTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private BookRepository bookRepository;

    @BeforeEach
    void init() {
        emf = Persistence.createEntityManagerFactory("myPersistence");
        em = emf.createEntityManager();
        bookRepository = new BookRepository();
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
        Book book = new Book();
        book.setTitle("Test Book");
        em.getTransaction().begin();
        bookRepository.create(book);
        em.getTransaction().commit();
        Book foundBook = bookRepository.findById(book.getId());
        assertNotNull(foundBook);
        assertEquals("Test Book", foundBook.getName());
    }
}