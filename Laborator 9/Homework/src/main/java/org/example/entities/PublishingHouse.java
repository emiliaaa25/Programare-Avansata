package org.example.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publishing_houses")
public class PublishingHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publishing_house_seq")
    @SequenceGenerator(name = "publishing_house_seq", sequenceName = "SEQ_GEN_IDENTITY", allocationSize = 1)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "publishingHouse")
    private Set<Book> books = new HashSet<>();

    public void setName(String penguinBooks) {
        this.name = penguinBooks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}