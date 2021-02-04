package com.cinema.dao.impl;

import com.cinema.dao.CinemaHallDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.lib.Dao;
import com.cinema.model.CinemaHall;
import com.cinema.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

@Dao
public class CinemaHallDaoImpl implements CinemaHallDao {
    @Override
    public List<CinemaHall> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<CinemaHall> allCinemaHallsQuery = session
                    .createQuery("FROM CinemaHall", CinemaHall.class);
            return allCinemaHallsQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to retrieve all CinemaHalls from DB", e);
        }
    }
}
