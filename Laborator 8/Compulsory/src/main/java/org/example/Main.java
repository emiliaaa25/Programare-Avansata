package org.example;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        try {
            var authors = new AuthorDAO();
            authors.create("William Shakespeare");

            System.out.println("Id ul autorului cu numele William Shakespeare este " + authors.findByName("William Shakespeare") );
            System.out.println("Numele autorului cu id-ul 1 este " + authors.findById(1));
            /*
            var genres = new GenreDAO();
            genres.create("Tragedy");
            Database.getConnection().commit();
            var books = new BookDAO(); //findByName
            books.create(1597,"Romeo and Juliet","William Shakespeare","Tragedy");
            books.create(1979,"The Hitchhiker's Guide to the Galaxy",
                    "Douglas Adams", "Science fiction, Comedy, Adventure");
            Database.getConnection().commit();
             */
            //TODO: print all the books in the database
            Database.getConnection().close();
        } catch (SQLException e) {
            System.out.println("In main");
            System.err.println(e);
            //Database.rollback();
        }

    }
}