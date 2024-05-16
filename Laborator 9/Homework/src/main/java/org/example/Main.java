package org.example;

import org.example.entities.Book;
import org.example.entities.PublishingHouse;
import org.example.repositories.BookRepository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {

        AppLogger.configure();

        BookRepository bookRepository = new BookRepository();
        PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setName("Penguin Books");

        Book book1 = new Book();
        book1.setTitle("Java Programming");
        book1.setPublishingHouse(publishingHouse.toString());

        bookRepository.create(book1);
        List<Book> books = bookRepository.findByName("Java");
        for (Book book : books) {
            System.out.println(book.getTitle());
        }

        Singleton.close();
    }
}