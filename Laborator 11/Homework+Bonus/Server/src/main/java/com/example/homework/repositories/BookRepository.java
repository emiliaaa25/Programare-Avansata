package com.example.homework.repositories;

import com.example.homework.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {
    private static final Logger log = LoggerFactory.getLogger(BookRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Book> bookRowMapper = (resultSet, rowNum) -> {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setName(resultSet.getString("name"));
        book.setAuthors(resultSet.getString("authors"));
        book.setYear(resultSet.getLong("year"));
        book.setGenre(resultSet.getString("genres"));
        book.setLanguage(resultSet.getString("language"));
        return book;
    };

    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM books", bookRowMapper);
    }

    public Book findById(long id) {
        return jdbcTemplate.query("SELECT * FROM books WHERE id = ?", bookRowMapper,id ).get(0);
    }

    public Book add(Book book) {
        jdbcTemplate.update("INSERT INTO books (id, name, authors, year, genres, language) VALUES (?, ?, ?, ?, ?, ?)",
                book.getId(), book.getName(), book.getAuthors(), book.getYear(), book.getGenre(), book.getLanguage());
        return book;
    }

    public Book update(long id, String name) {
        jdbcTemplate.update("UPDATE books SET name = ? WHERE id = ?", name, id);
        return findById(id);
    }

    public void delete(long id) {
        Book book = findById(id);
        jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }
}
