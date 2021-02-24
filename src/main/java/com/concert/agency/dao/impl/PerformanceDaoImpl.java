package com.concert.agency.dao.impl;

import com.concert.agency.dao.PerformanceDao;
import com.concert.agency.exception.DataProcessingException;
import com.concert.agency.model.Performance;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PerformanceDaoImpl extends AbstractDaoImpl<Performance> implements PerformanceDao {
    @Autowired
    public PerformanceDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Performance> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Performance> allMoviesQuery = session
                    .createQuery("FROM Performance", Performance.class);
            return allMoviesQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to retrieve all Performances from DB", e);
        }
    }

    @Override
    public Performance getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Performance m WHERE m.id=:id", Performance.class)
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to retrieve Performance, id=" + id, e);
        }
    }
}
