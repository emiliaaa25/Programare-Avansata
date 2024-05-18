package org.example.entities;

import javax.persistence.*;


@Entity
@Table(name = "books")
@NamedQuery(
        name = "Book.findByName",
        query = "SELECT b FROM Book b WHERE b.name LIKE :name"
)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "SEQ_GEN_IDENTITY", allocationSize = 1)
    private int id;

   @Column(name = "name")
    private String name;

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Column(name = "publishingHouse")
    private String publishingHouse;

   @Column(name = "authors")
    private String authors;

   @Column(name = "year")
    private int year;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenres(String genre) {
        this.genre = genre;
    }

    @Column(name = "language")
    private String language;

    @Column(name = "genres")
    private String genre;


    public void setTitle(String javaProgramming) {
        this.name=javaProgramming;
    }

    public boolean getTitle() {

        return false;
    }
}
