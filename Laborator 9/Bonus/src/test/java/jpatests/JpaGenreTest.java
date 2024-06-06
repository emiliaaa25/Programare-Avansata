package jpatests;

import org.example.entities.Genre;
import org.example.repositories.GenreRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JpaGenreTest {
    @Test
    void findGenre() {
        Genre genre = new Genre();
        genre.setName("Fiction");
        GenreRepository genreRepository = new GenreRepository(Genre.class);
        genreRepository.create(genre);
        Genre foundGenre = genreRepository.findById(genre.getId());
        assertNotNull(foundGenre);
        assertEquals("Fiction", foundGenre.getName());
    }
}
