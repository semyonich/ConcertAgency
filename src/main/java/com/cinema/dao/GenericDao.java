package com.cinema.dao;

import com.cinema.exception.DataProcessingException;
import com.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface GenericDao<T> {
    default T add(T value) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(value);
            transaction.commit();
            return value;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to add " + value + "to DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
