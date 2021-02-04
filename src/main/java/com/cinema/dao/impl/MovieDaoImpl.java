package com.cinema.dao.impl;

import com.cinema.dao.MovieDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.lib.Dao;
import com.cinema.model.Movie;
import com.cinema.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Dao
public class MovieDaoImpl implements MovieDao {
    @Override
    public List<Movie> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Movie> allMoviesQuery = session.createQuery("FROM Movie", Movie.class);
            return allMoviesQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to retrieve all Movies from DB", e);
        }
    }
}
