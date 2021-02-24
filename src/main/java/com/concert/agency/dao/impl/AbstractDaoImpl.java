package com.concert.agency.dao.impl;

import com.concert.agency.dao.AbstractDao;
import com.concert.agency.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {
    protected final SessionFactory sessionFactory;

    public AbstractDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T add(T entity) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to add " + entity + "to DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
