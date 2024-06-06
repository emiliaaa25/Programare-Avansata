package org.example.dao;

import org.example.entities.Genre;

import java.sql.SQLException;
import java.util.List;

public interface GenreDAO {
    void create(String name) throws SQLException;

    Integer findByName(String name) throws SQLException;

    Genre findById(int id) throws SQLException;

    List<Genre> findAll() throws SQLException;
}
