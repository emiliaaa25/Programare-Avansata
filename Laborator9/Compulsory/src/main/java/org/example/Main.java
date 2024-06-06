package org.example;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            var books = new BookDAO();
            ImportData.importData(books);
            System.out.println("\nBONUS:");
            ReadingList readingList = new ReadingList();
            readingList.createGraph();


            // AICI PRINTEAZA TOATE LISTELE DE CITIRE
            String[] list = readingList.getReadingList();
            for (String i : list) {
                System.out.println("ANOTHER LIST:");
                System.out.println(i);
            }
            DBCPDataSource.getConnection().close();
        } catch (SQLException e) {
            System.out.println("In main");
            System.err.println(e);
            DBCPDataSource.getConnection().rollback();
        }

    }
}