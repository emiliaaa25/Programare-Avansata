package com.example.homework.repositories;

import com.example.homework.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepository {
    private static final Logger log = LoggerFactory.getLogger(AuthorRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Author> findAll() {
        List<Author> authors = jdbcTemplate.query("SELECT * FROM authors", (resultSet, rowNum) -> {
            Author author = new Author();
            author.setId(resultSet.getLong("id"));
            author.setName(resultSet.getString("name"));
            return author;
        });
        return authors;
    }
}