package org.example.repositories;

import org.example.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public abstract class AbstractRepository<T> {
    private Class<T> entityClass;

    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    public void create(T entity) {
        EntityManager em = Singleton.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(entity);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public T findById(Long id) {
        EntityManager em = Singleton.getEntityManagerFactory().createEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    public List<T> findByName(String name) {
        EntityManager em = Singleton.getEntityManagerFactory().createEntityManager();
        try {
            return em.createNamedQuery(entityClass.getSimpleName() + ".findByName", entityClass)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
