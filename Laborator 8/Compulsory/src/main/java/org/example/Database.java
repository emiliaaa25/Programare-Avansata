package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL ="jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "student";
    private static final String PASSWORD = "STUDENT";
    private static Connection connection = null;

    public Database() {
        createConnection();
    }
    public static Connection getConnection() {
        return connection;
    }
    public static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);

            createBookTable();
            createAuthorsTable();
            createGenreTable();

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            System.err.println(e);
        }
    }

    public static void createBookTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE books");
        statement.execute("CREATE TABLE books (id NUMBER PRIMARY KEY, name VARCHAR(255) NOT NULL)");
    }

    public static void createAuthorsTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE authors");
        statement.execute("CREATE TABLE authors (id NUMBER PRIMARY KEY, name VARCHAR(255) NOT NULL)");
    }

    public static void createGenreTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE genres");
        statement.execute("CREATE TABLE genres (id NUMBER PRIMARY KEY)");
    }
    public static void closeConnection() throws SQLException {// TODO
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}