package com.cinema.dao;

import com.cinema.model.ShoppingCart;
import com.cinema.model.User;

public interface ShoppingCartDao extends GenericDao<ShoppingCart> {
    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
