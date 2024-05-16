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
}