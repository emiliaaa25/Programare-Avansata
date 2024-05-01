package org.example;

import org.example.entities.Book;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class BookRespository {
    private final EntityManagerFactory entityManagerFactory = Singleton.getEntityManagerFactory();

    public void create(Book book) {
        entityManagerFactory.createEntityManager().persist(book);
    }

    public Book findById(int id) {
        return entityManagerFactory.createEntityManager().find(Book.class, id);
    }
    public List<Book> findByName(String name) {
        return entityManagerFactory.createEntityManager().createQuery("SELECT b FROM Book b WHERE b.name = :name", Book.class)
                .setParameter("name", name)
                .getResultList();
    }
}
