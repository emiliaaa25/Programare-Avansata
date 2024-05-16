package org.example.repositories;

import org.example.entities.Genre;

public class GenreRepository extends AbstractRepository<Genre> {

    public GenreRepository(Class<Genre> entityClass) {
        super(entityClass);
    }
}
