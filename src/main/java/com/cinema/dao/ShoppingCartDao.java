package com.cinema.dao;

import com.cinema.model.ShoppingCart;
import com.cinema.model.User;

public interface ShoppingCartDao extends AbstractDao<ShoppingCart> {
    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
