package org.example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Singleton {
    private static final EntityManagerFactory instance = Persistence.createEntityManagerFactory("myPersistence");

    private Singleton() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return instance;
    }
}
