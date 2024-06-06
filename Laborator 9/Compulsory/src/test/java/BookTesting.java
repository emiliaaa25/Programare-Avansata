import org.example.BookRespository;
import org.example.entities.Book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookTesting {
    private BookRespository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRespository();
    }

    @Test
    void create() {
        Book book = new Book();
        book.setName("Anna Karenina");
        bookRepository.create(book);
        Book foundBook = bookRepository.findById(70);
        assertEquals("Anna Karenina", foundBook.getName());
    }

    @Test
    void findById() {
        Book foundBook = bookRepository.findById(1);
        assertNotNull(foundBook);
    }

    @Test
    void findByName() {
        List<Book> foundBooks = bookRepository.findByName("Test Book");
        assertTrue(foundBooks.isEmpty());
    }
}