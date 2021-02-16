package com.cinema.dao.impl;

import com.cinema.dao.CinemaHallDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.model.CinemaHall;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl extends AbstractDaoImpl<CinemaHall> implements CinemaHallDao {
    @Autowired
    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<CinemaHall> allCinemaHallsQuery = session
                    .createQuery("FROM CinemaHall", CinemaHall.class);
            return allCinemaHallsQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to retrieve all CinemaHalls from DB", e);
        }
    }

    @Override
    public CinemaHall getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM CinemaHall ch WHERE ch.id=:id", CinemaHall.class)
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to retrieve CinemaHall, id=" + id, e);
        }
    }
}
