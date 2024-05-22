package org.example.dao;


import org.example.entities.PublishingHouse;

import java.sql.SQLException;
import java.util.List;

public interface PublishingHouseDAO {
    void create(int id, String name) throws SQLException;

    Integer findByName(String name) throws SQLException;

    PublishingHouse findById(int id) throws SQLException;

    List<PublishingHouse> findAll() throws SQLException;
}

