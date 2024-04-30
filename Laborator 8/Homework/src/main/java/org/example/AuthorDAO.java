package org.example;

import java.sql.*;

public class AuthorDAO {
    static int id = 0;

    public void create(String name) throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into authors (id, name) values (?, ?)")) {
            pstmt.setInt(1, ++id);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select id from authors where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select name from authors where id=" + id)) {
            return rs.next() ? rs.getString(1) : null;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    public void findAll() throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select * from authors")) {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
