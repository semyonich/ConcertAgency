package com.concert.agency.service.impl;

import com.concert.agency.dao.ShoppingCartDao;
import com.concert.agency.dao.TicketDao;
import com.concert.agency.model.Event;
import com.concert.agency.model.ShoppingCart;
import com.concert.agency.model.Ticket;
import com.concert.agency.model.User;
import com.concert.agency.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartDao shoppingCartDao;
    private final TicketDao ticketDao;

    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, TicketDao ticketDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.ticketDao = ticketDao;
    }

    @Override
    public void addSession(Event event, User user) {
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setEvent(event);
        ShoppingCart userCart = shoppingCartDao.getByUser(user);
        ticketDao.add(ticket);
        userCart.getTickets().add(ticket);
        shoppingCartDao.update(userCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getTickets().clear();
        shoppingCartDao.update(shoppingCart);
    }
}
