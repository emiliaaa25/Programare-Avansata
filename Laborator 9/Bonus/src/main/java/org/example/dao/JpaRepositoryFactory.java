package org.example.dao;

import org.example.entities.Author;
import org.example.entities.Book;
import org.example.entities.Genre;
import org.example.repositories.AbstractRepository;
import org.example.repositories.AuthorRepository;
import org.example.repositories.BookRepository;
import org.example.repositories.GenreRepository;

public class JpaRepositoryFactory implements RepositoryFactory {
    @Override
    public AbstractRepository<Author> createAuthorDAO() {
        return new AuthorRepository(Author.class);
    }

    @Override
    public AbstractRepository<Book> createBookDAO() {
        return new BookRepository();
    }

    @Override
    public AbstractRepository<Genre> createGenreDAO() {
        return new GenreRepository(Genre.class);
    }
}