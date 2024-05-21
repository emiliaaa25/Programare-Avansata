package org.example.dao;

import org.example.DBCPDataSource;
import org.example.entities.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JdbcGenreDAO implements GenreDAO {
    static int id = 0;
    private final Connection connection;

    public JdbcGenreDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(String name) throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into genres (id, name) values (?, ?)")) {
            pstmt.setInt(1, ++id);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Integer findByName(String name) throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select id from genres where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }


    @Override
    public Genre findById(int id) throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from genres where id=" + id)) {
            if (rs.next()) {
                return new Genre(rs.getInt(1), rs.getString(2));
            } else {
                return null;
            }
        }
    }

    @Override
    public List<Genre> findAll() throws SQLException {
        List<Genre> genres = new ArrayList<>();
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select * from genres")) {
            while (rs.next()) {
                Genre genre = new Genre(rs.getInt(1), rs.getString(2));
                genres.add(genre);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return genres;
    }
}
