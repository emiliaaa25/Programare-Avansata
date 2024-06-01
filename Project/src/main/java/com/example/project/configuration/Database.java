package com.example.project.configuration;
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


            createElevatorTable();

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            System.err.println(e);
        }
    }

    public static void createElevatorTable() throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE elevator");
            statement.execute("CREATE TABLE elevator (id NUMBER PRIMARY KEY, name VARCHAR(255), floor NUMBER, isItHere BOOLEAN,shouldStop BOOLEAN)");
        } catch (SQLException e) {
            System.out.println("Table elevator already exists");
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