package org.example.dao;

import org.example.entities.Author;

import java.sql.SQLException;
import java.util.List;

public interface AuthorDAO {
    void create(String name) throws SQLException;

    int findByName(String name) throws SQLException;

    Author findById(int id) throws SQLException;

    List<Author> findAll() throws SQLException;

}
