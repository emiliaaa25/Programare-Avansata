package org.example.dao;

import org.example.entities.Author;
import org.example.entities.Book;
import org.example.entities.Genre;
import org.example.repositories.AbstractRepository;

public interface RepositoryFactory  {
    AbstractRepository<Author> createAuthorDAO();
    AbstractRepository<Book> createBookDAO();
    AbstractRepository<Genre> createGenreDAO();
}
