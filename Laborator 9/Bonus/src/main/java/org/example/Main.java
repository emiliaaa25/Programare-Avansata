package org.example;

import org.example.dao.*;
import org.example.entities.Author;
import org.example.entities.Book;
import org.example.entities.Genre;
import org.example.repositories.AbstractRepository;
import java.sql.SQLException;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;

public class Main {
    public static void main(String[] args) throws SQLException {
        DAOFactory daoFactory = DAOFactoryProducer.getDAOFactory();
        RepositoryFactory repoFactory = DAOFactoryProducer.getRepositoryFactory();

        if (daoFactory != null) {
            System.out.println("DAO Factory");
            AuthorDAO authorDAO = daoFactory.createAuthorDAO();
            BookDAO bookDAO = daoFactory.createBookDAO();
            GenreDAO genreDAO = daoFactory.createGenreDAO();
            PublishingHouseDAO publishingHouseDAO = daoFactory.createPublishingHouseDAO();

            authorDAO.create("John Doe");
            bookDAO.create(2021, "Example Book", new String[]{"John Doe"}, new String[]{"Example Genre"}, "English", "Example Publishing House");
            genreDAO.create("Example Genre");
            publishingHouseDAO.create(0, "Example Publishing House");
            String book = bookDAO.findById(92);
            System.out.println(book);

        } else if (repoFactory != null) {
            AbstractRepository<Author> authorRepo = repoFactory.createAuthorDAO();
            AbstractRepository<Book> bookRepo = repoFactory.createBookDAO();
            AbstractRepository<Genre> genreRepo = repoFactory.createGenreDAO();
            authorRepo.create(new Author("John Doe"));
            bookRepo.create(new Book("Example Book", authorRepo.findById(1)));
            genreRepo.create(new Genre("Example Genre", bookRepo.findById(1)));
            Book book = bookRepo.findById(92);
            System.out.println(book.getName());

        } else {
            throw new RuntimeException("No valid factory found");
        }

        Model model = new Model("Book selection");

        Book[] books = new Book[]{
                new Book(0, "Book 1", 2000),
                new Book(1, "Book 2", 2001),
                new Book(2, "Book 3", 2002),
                new Book(3, "Book 4", 2003),
                new Book(4, "Book 5", 2004),
                new Book(5, "Book 6", 2005),
                new Book(6, "Book 7", 2006),
                new Book(7, "Book 8", 2007),
                new Book(8, "Book 9", 2008),
                new Book(9, "Book 10", 2009)
        };

        BoolVar[] isIncluded = model.boolVarArray("isIncluded", books.length);

        IntVar[] years = new IntVar[books.length];

        for (int i = 0; i < books.length; i++) {
            years[i] = model.intVar("year" + i, books[i].getYear());
            model.ifThen(model.arithm(isIncluded[i], "=", 1), model.arithm(years[i], "=", books[i].getYear()));
        }

        int k = 5;
        model.sum(isIncluded, ">=", k).post();

        char startingLetter = 'B';
        for (Book book : books) {
            if (book.getName().charAt(0) != startingLetter) {
                isIncluded[book.getId()].eq(0).post();
            }
        }

        int p = 5;
        for (int i = 0; i < books.length; i++) {
            for (int j = i + 1; j < books.length; j++) {
                model.ifThen(
                        isIncluded[i].eq(1).and(isIncluded[j].eq(1)).boolVar(),
                        model.arithm(years[i], "-", years[j], "<=", p)
                );
            }
        }

        if (model.getSolver().solve()) {
            for (int i = 0; i < books.length; i++) {
                if (isIncluded[i].getValue() == 1) {
                    System.out.println(books[i].getName());
                }
            }
        } else {
            System.out.println("No solution found");
        }
    }
}
