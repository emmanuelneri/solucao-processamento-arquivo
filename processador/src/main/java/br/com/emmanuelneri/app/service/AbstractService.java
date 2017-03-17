package br.com.emmanuelneri.app.service;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractService{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public <T> void persist(T object) {
        this.entityManager.persist(object);
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }
}