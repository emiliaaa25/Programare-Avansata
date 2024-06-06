package org.example.dao;

import org.example.entities.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO {
    void create(int year, String name, String[] author, String[] genre, String language, String publishingHouseName) throws SQLException;

    Integer findByName(String name) throws SQLException;

    String findById(int id) throws SQLException;

    List<Book> findAll() throws SQLException;
}
