package com.concert.agency.dao.impl;

import com.concert.agency.dao.ConcertHallDao;
import com.concert.agency.exception.DataProcessingException;
import com.concert.agency.model.ConcertHall;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConcertHallDaoImpl extends AbstractDaoImpl<ConcertHall> implements ConcertHallDao {
    @Autowired
    public ConcertHallDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<ConcertHall> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<ConcertHall> allConcertHallsQuery = session
                    .createQuery("FROM ConcertHall", ConcertHall.class);
            return allConcertHallsQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to retrieve all ConcertHalls from DB", e);
        }
    }

    @Override
    public ConcertHall getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM ConcertHall ch WHERE ch.id=:id", ConcertHall.class)
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to retrieve ConcertHall, id=" + id, e);
        }
    }
}
