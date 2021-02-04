package com.cinema.dao.impl;

import com.cinema.dao.MovieSessionDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.lib.Dao;
import com.cinema.model.MovieSession;
import com.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.hibernate.Session;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        LocalDateTime fromDateTime = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime toDateTime = LocalDateTime.of(date, LocalTime.MAX);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
