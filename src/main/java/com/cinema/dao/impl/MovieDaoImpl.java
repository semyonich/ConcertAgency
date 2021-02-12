package com.cinema.dao.impl;

import com.cinema.dao.MovieDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.model.Movie;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl extends AbstractDaoImpl<Movie> implements MovieDao {
    @Autowired
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = getSessionFactory().openSession()) {
            Query<Movie> allMoviesQuery = session.createQuery("FROM Movie", Movie.class);
            return allMoviesQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to retrieve all Movies from DB", e);
        }
    }

    @Override
    public Movie getById(Long id) {
        try (Session session = getSessionFactory().openSession()) {
            return session.createQuery("FROM Movie m WHERE m.id=:id", Movie.class)
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to retrieve Movie, id=" + id, e);
        }
    }
}
