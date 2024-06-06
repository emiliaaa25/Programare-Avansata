package jpatests;

import org.example.entities.Author;
import org.junit.jupiter.api.Test;
import org.example.repositories.AuthorRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JpaAuthorTest {

    @Test
    void findAuthor() {
        Author author = new Author();
        author.setName("John Doe");
        AuthorRepository authorRepository = new AuthorRepository(Author.class);
        authorRepository.create(author);
        Author foundAuthor = authorRepository.findById(author.getId());
        assertNotNull(foundAuthor);
        assertEquals("John Doe", foundAuthor.getName());
    }
}
