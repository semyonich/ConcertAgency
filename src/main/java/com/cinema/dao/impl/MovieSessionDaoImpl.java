package com.cinema.dao.impl;

import com.cinema.dao.MovieSessionDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.model.MovieSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl extends AbstractDaoImpl<MovieSession> implements MovieSessionDao {
    @Autowired
    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        LocalDateTime fromDateTime = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime toDateTime = LocalDateTime.of(date, LocalTime.MAX);
        try (Session session = getSessionFactory().openSession()) {
            return session.createQuery("FROM MovieSession ms "
                    + "INNER JOIN FETCH ms.movie AS m "
                    + "INNER JOIN FETCH ms.cinemaHall "
                    + "WHERE m.id=:movieId AND ms.showTime BETWEEN :from AND :to",
                    MovieSession.class)
                    .setParameter("movieId", movieId).setParameter("from", fromDateTime)
                    .setParameter("to", toDateTime).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get available MovieSessions from DB", e);
        }
    }

    @Override
    public void update(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(movieSession);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to update " + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void remove(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(movieSession);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to remove " + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
