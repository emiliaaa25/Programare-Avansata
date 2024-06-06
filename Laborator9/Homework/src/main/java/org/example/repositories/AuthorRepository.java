package org.example.repositories;

import org.example.entities.Author;

public class AuthorRepository extends AbstractRepository<Author> {
    public AuthorRepository(Class<Author> entityClass) {
        super(entityClass);
    }
}
