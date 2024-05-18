import org.example.entities.Book;
import org.example.entities.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class GenreTest {

    @Test
    public void testGenreBookRelationship() {
        Genre fantasy = new Genre();
        fantasy.setName("Fantasy");
        fantasy.setId(1);

        Book book1 = new Book();
        book1.setTitle("The Lord of the Rings");

        Book book2 = new Book();
        book2.setTitle("Harry Potter");

        Set<Book> fantasyBooks = new HashSet<>();
        fantasyBooks.add(book1);
        fantasyBooks.add(book2);
        fantasy.setBooks(fantasyBooks);

        Assertions.assertTrue(fantasy.getBooks().contains(book1), "The Lord of the Rings");
        Assertions.assertTrue(fantasy.getBooks().contains(book1));
        Assertions.assertTrue(fantasy.getBooks().contains(book2));
    }
}
