package com.cinema.dao.impl;

import com.cinema.dao.TicketDao;
import com.cinema.model.Ticket;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDaoImpl extends AbstractDaoImpl<Ticket> implements TicketDao {
    @Autowired
    public TicketDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
