package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            var books = new BookDAO();
            ImportData.importData(books);
            books.findAll();
            DBCPDataSource.getConnection().close();
        } catch (SQLException e) {
            System.out.println("In main");
            System.err.println(e);
            DBCPDataSource.getConnection().rollback();
        }

    }
}