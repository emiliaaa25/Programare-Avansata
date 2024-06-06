package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPDataSource {
    public static Database db = new Database();

    public static Connection getConnection() throws SQLException {
        return Database.getConnection();
    }

    private DBCPDataSource() {
    }
}