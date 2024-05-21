package org.example.repositories;

import org.example.Singleton;
import org.example.entities.Book;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookRepository extends AbstractRepository<Book> {

    public BookRepository() {
        super(Book.class);
    }
}
