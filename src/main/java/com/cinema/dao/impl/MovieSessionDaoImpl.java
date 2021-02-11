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
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private final SessionFactory sessionFactory;

    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to add " + movieSession + "to DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        LocalDateTime fromDateTime = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime toDateTime = LocalDateTime.of(date, LocalTime.MAX);
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM MovieSession ms "
                    + "INNER JOIN FETCH ms.movie AS m "
                    + "INNER JOIN FETCH ms.cinemaHall "
                    + "WHERE m.id=:movieId AND showTime BETWEEN :from AND :to", MovieSession.class)
                    .setParameter("movieId", movieId).setParameter("from", fromDateTime)
                    .setParameter("to", toDateTime).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get available MovieSessions from DB", e);
        }
    }
}
