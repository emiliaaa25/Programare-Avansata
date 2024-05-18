import org.example.Singleton;
import org.example.entities.Book;
import org.example.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookTesting {
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
        setUpTestData();
    }

    private void setUpTestData() {
        EntityManager em = Singleton.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            em.createQuery("DELETE FROM Book").executeUpdate();
            Book book1 = new Book();
            book1.setName("Anna Karenina");
            book1.setId(1);
            em.persist(book1);
            Book book2 = new Book();
            book2.setName("Test Book");
            book2.setId(2);
            em.persist(book2);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Test
    void create() {
        Book book = new Book();
        book.setName("New Book");
        bookRepository.create(book);
        Book foundBook = bookRepository.findById(book.getId());
        assertNotNull(foundBook);
        assertEquals("New Book", foundBook.getName());
    }

    @Test
    void findById() {
        Book foundBook = bookRepository.findById(1);
        assertNotNull(foundBook);
    }

    @Test
    void findByName() {
        Book foundBooks = bookRepository.findByName("Test Book");
        assertEquals("Test Book", foundBooks.getName());
    }
}
