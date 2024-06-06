package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
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
            createBookAuthorTable();
            createBookGenreTable();
            createPublishingHouseTable();

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            System.err.println(e);
        }
    }

    public static void createPublishingHouseTable() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE publishingHouse");
            statement.execute("CREATE TABLE publishingHouse (id NUMBER PRIMARY KEY, name VARCHAR(255) NOT NULL)");
        } catch (SQLException e) {
            System.out.println("Table publishingHouse already exists");
        }
    }
    public static void createBookTable() throws SQLException {
        try {
            Statement statement = connection.createStatement();
           statement.execute("DROP TABLE books");
            statement.execute("CREATE TABLE books (id NUMBER PRIMARY KEY, name VARCHAR(255) NOT NULL, authors VARCHAR(255), year NUMBER, publishingHouse  VARCHAR(255), language VARCHAR(255), genres VARCHAR(255))");
        } catch (SQLException e) {
            System.out.println("Table books already exists");
        }
    }

    public static void createAuthorsTable() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE authors");
            statement.execute("CREATE TABLE authors ( id NUMBER PRIMARY KEY, name VARCHAR(255) NOT NULL)");
        } catch (SQLException e) {
            System.out.println("Table authors already exists");
        }
    }

    public static void createGenreTable() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE genres");
            statement.execute("CREATE TABLE genres (id NUMBER PRIMARY KEY,name VARCHAR(255) NOT NULL)");
        } catch (SQLException e) {
            System.out.println("Table genres already exists");
        }
    }

    public static void createBookAuthorTable() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE book_author");
            statement.execute("CREATE TABLE book_author ( book_id NUMBER,author_id NUMBER,PRIMARY KEY (book_id, author_id),FOREIGN KEY (book_id) REFERENCES books(id),FOREIGN KEY (author_id) REFERENCES authors(id))");
        } catch (SQLException e) {
            System.out.println("Table book_author already exists");
            System.err.println(e);
        }
    }

    public static void createBookGenreTable() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE book_genre");
            statement.execute("CREATE TABLE book_genre (book_id NUMBER,genre_id NUMBER,PRIMARY KEY (book_id, genre_id),FOREIGN KEY (book_id) REFERENCES books(id),FOREIGN KEY (genre_id) REFERENCES genres(id)");
        } catch (SQLException e) {
            System.out.println("Table book_genre already exists");
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}