package com.concert.agency.dao.impl;

import com.concert.agency.dao.OrderDao;
import com.concert.agency.exception.DataProcessingException;
import com.concert.agency.model.Order;
import com.concert.agency.model.User;
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
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT DISTINCT o FROM Order o "
                    + "LEFT JOIN FETCH o.tickets AS t "
                    + "LEFT JOIN FETCH t.event AS e "
                    + "LEFT JOIN FETCH e.performance "
                    + "LEFT JOIN FETCH e.concertHall "
                    + "LEFT JOIN FETCH o.user "
                    + "WHERE o.user=:user", Order.class)
                    .setParameter("user", user).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get Orders made by " + user, e);
        }
    }
}
