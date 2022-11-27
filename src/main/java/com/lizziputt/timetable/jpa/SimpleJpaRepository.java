package com.lizziputt.timetable.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SimpleJpaRepository<T extends Persistable<ID>, ID> implements CrudRepository<T, ID> {

    private final EntityManager em;

    private final Class<T> domainType;

    @Override
    public void delete(T entity) {
        Assert.notNull(entity, "Entity must not be null");
        EntityTransaction transaction = null;
        T existing = em.find(domainType, entity.getId());
        if (existing != null) {
            try {
                transaction = em.getTransaction();
                transaction.begin();
                boolean isPresentInContext = em.contains(entity);

                if (isPresentInContext) {
                    em.remove(entity);
                } else {
                    T contextBoundedEntity = em.merge(entity);
                    em.remove(contextBoundedEntity);
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public <S extends T> S save(S entity) {
        Assert.notNull(entity, "Entity must not be null");
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }

        return entity;
    }

    @Override
    public <S extends T> S update(S entity) {
        Assert.notNull(entity, "Entity must not be null");
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }

        return entity;
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(domainType);
        Root<T> root = query.from(domainType);
        query.select(root);
        TypedQuery<T> resultQuery = em.createQuery(query);
        return resultQuery.getResultList();
    }

    @Override
    public Optional<T> findById(ID id) {
        Assert.notNull(id, "Entity ID must not be null");
        return Optional.ofNullable(em.find(domainType, id));
    }

    private static class Assert {
        public static void notNull(Object object, String message) {
            if (object == null) {
                throw new IllegalArgumentException(message);
            }
        }
    }
}
