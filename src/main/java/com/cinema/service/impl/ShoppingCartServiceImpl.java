package com.cinema.service.impl;

import com.cinema.dao.ShoppingCartDao;
import com.cinema.dao.TicketDao;
import com.cinema.lib.Inject;
import com.cinema.lib.Service;
import com.cinema.model.MovieSession;
import com.cinema.model.ShoppingCart;
import com.cinema.model.Ticket;
import com.cinema.model.User;
import com.cinema.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;
    @Inject
    private TicketDao ticketDao;

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setMovieSession(movieSession);
        ticketDao.add(ticket);
        ShoppingCart userCart = shoppingCartDao.getByUser(user);
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
