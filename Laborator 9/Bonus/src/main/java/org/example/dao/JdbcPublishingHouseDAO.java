package org.example.dao;

import org.example.entities.PublishingHouse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPublishingHouseDAO implements PublishingHouseDAO {
    private final Connection connection;

    public JdbcPublishingHouseDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(int id, String name) throws SQLException {
        String sql = "INSERT INTO publishingHouse (id, name) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
        }
    }

    @Override
    public Integer findByName(String name) throws SQLException {
        String sql = "SELECT id FROM publishingHouse WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public PublishingHouse findById(int id) throws SQLException {
        String sql = "SELECT * FROM publishingHouse WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    PublishingHouse publishingHouse = new PublishingHouse();
                    publishingHouse.setId(rs.getInt("id"));
                    publishingHouse.setName(rs.getString("name"));
                    return publishingHouse;
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public List<PublishingHouse> findAll() throws SQLException {
        List<PublishingHouse> publishingHouses = new ArrayList<>();
        String sql = "SELECT * FROM publishingHouse";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                PublishingHouse publishingHouse = new PublishingHouse();
                publishingHouse.setId(rs.getInt("id"));
                publishingHouse.setName(rs.getString("name"));
                publishingHouses.add(publishingHouse);
            }
        }
        return publishingHouses;
    }
}