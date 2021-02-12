package com.cinema.dao.impl;

import com.cinema.dao.OrderDao;
import com.cinema.exception.DataProcessingException;
import com.cinema.model.Order;
import com.cinema.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends AbstractDaoImpl<Order> implements OrderDao {
    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        try (Session session = getSessionFactory().openSession()) {
            return session.createQuery("SELECT DISTINCT o FROM Order o "
                    + "LEFT JOIN FETCH o.tickets AS t "
                    + "LEFT JOIN FETCH t.movieSession AS ms "
                    + "LEFT JOIN FETCH ms.movie "
                    + "LEFT JOIN FETCH ms.cinemaHall "
                    + "LEFT JOIN FETCH o.user "
                    + "WHERE o.user=:user", Order.class)
                    .setParameter("user", user).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get Orders made by " + user, e);
        }
    }
}
