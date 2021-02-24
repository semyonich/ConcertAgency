package com.concert.agency.service;

import com.concert.agency.model.Event;
import com.concert.agency.model.ShoppingCart;
import com.concert.agency.model.User;

public interface ShoppingCartService {
    void addSession(Event event, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
