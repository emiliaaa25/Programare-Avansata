package org.example.dao;

import org.example.DBCPDataSource;
import org.example.entities.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcAuthorDAO implements AuthorDAO{
    static int id = 0;
    private final Connection connection;

    public JdbcAuthorDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(String name) throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into authors (id, name) values (?, ?)")) {
            pstmt.setInt(1, ++id);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        }
    }

    @Override
    public int findByName(String name) throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from authors where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : -1;
        }
    }

    @Override
    public Author findById(int id) throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from authors where id=" + id)) {
            if (rs.next()) {
                return new Author(rs.getInt(1), rs.getString(2));
            }
        }
        return null;
    }

    @Override
    public List<Author> findAll() throws SQLException {
        List<Author> authors = new ArrayList<>();
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from authors")) {
            while (rs.next()) {
                Author author = new Author(rs.getInt(1), rs.getString(2));
                authors.add(author);
            }
        }
        return authors;
    }
}
