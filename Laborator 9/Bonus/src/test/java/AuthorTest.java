import org.example.entities.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.example.entities.Author;

public class AuthorTest {

    @Test
    void testSetNameAndGetters() {
        Author author = new Author();
        author.setName("Test Author");
        assertEquals("Test Author", author.getName());
    }

    @Test
    void testAddBook() {
        Author author = new Author();
        Book book = new Book();
        author.addBook(book);
        assertEquals(1, author.getBooks().size());
    }

}
