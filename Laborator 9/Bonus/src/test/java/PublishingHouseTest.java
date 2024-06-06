import org.example.entities.Book;
import org.example.entities.PublishingHouse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class PublishingHouseTest {

    @Test
    public void testPublishingHouseBookRelationship() {
        PublishingHouse penguinBooks = new PublishingHouse();
        penguinBooks.setName("Penguin Books");
        penguinBooks.setId(1);

        Book book1 = new Book();
        book1.setTitle("1984");
        book1.setPublishingHouse(penguinBooks);

        Book book2 = new Book();
        book2.setTitle("Animal Farm");
        book2.setPublishingHouse(penguinBooks);

        Set<Book> penguinBooksBooks = new HashSet<>();
        penguinBooksBooks.add(book1);
        penguinBooksBooks.add(book2);
        penguinBooks.setBooks(penguinBooksBooks);

        Assertions.assertTrue(penguinBooks.getBooks().contains(book1));
        Assertions.assertTrue(penguinBooks.getBooks().contains(book2));

    }
}
