package org.example.repositories;

import org.example.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class AbstractRepository<T> {
    private static final Logger logger = Logger.getLogger(AbstractRepository.class.getName());
    private final Class<T> entityClass;

    static {
        try {
            // Set up file handler
            FileHandler fileHandler = new FileHandler("repository.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);

            // Set up console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.ALL);

            // Add handlers to the logger
            logger.addHandler(fileHandler);
            logger.addHandler(consoleHandler);
            logger.setLevel(Level.ALL);
            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to initialize log handlers", e);
        }
    }

    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        EntityManager em = Singleton.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        long startTime = System.currentTimeMillis();
        try {
            logger.log(Level.INFO, "Creating entity: {0}", entity);
            et.begin();
            em.persist(entity);
            et.commit();
            logger.log(Level.INFO, "Entity created successfully: {0}", entity);
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            logger.log(Level.SEVERE, "Error while creating entity", e);
            throw e;
        } finally {
            em.close();
            long endTime = System.currentTimeMillis();
            logger.log(Level.INFO, "Execution time for create method: {0} ms", (endTime - startTime));
            logger.log(Level.INFO, "EntityManager closed after creating entity");
        }
    }

    public T findById(int id) {
        EntityManager em = Singleton.getEntityManagerFactory().createEntityManager();
        long startTime = System.currentTimeMillis();
        try {
            logger.log(Level.INFO, "Finding entity by ID: {0}", id);
            T entity = em.find(entityClass, id);
            logger.log(Level.INFO, "Found entity: {0}", entity);
            return entity;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while finding entity by ID", e);
            throw e;
        } finally {
            em.close();
            long endTime = System.currentTimeMillis();
            logger.log(Level.INFO, "Execution time for findById method: {0} ms", (endTime - startTime));
            logger.log(Level.INFO, "EntityManager closed after finding entity by ID");
        }
    }

    public T findByName(String name) {
        EntityManager em = Singleton.getEntityManagerFactory().createEntityManager();
        long startTime = System.currentTimeMillis();
        try {
            logger.log(Level.INFO, "Finding entity by name: {0}", name);
            T result = em.createNamedQuery(entityClass.getSimpleName() + ".findByName", entityClass)
                    .setParameter("name", "%" + name + "%")
                    .getSingleResult();
            logger.log(Level.INFO, "Found entity: {0}", result);
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while finding entity by name", e);
            throw e;
        } finally {
            em.close();
            long endTime = System.currentTimeMillis();
            logger.log(Level.INFO, "Execution time for findByName method: {0} ms", (endTime - startTime));
            logger.log(Level.INFO, "EntityManager closed after finding entity by name");
        }
    }
}
