package org.example;

import java.sql.*;

public class GenreDAO {
    static int id = 0;


    public void create(String name) throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into genres (id, name) values (?, ?)")) {
            pstmt.setInt(1, ++id);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select id from genres where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select name from genres where id=" + id)) {
            return rs.next() ? rs.getString(1) : null;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    public void findAll() throws SQLException {
        Connection con = DBCPDataSource.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("select * from genres")) {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
