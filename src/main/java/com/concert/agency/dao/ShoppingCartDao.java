package com.concert.agency.dao;

import com.concert.agency.model.ShoppingCart;
import com.concert.agency.model.User;

public interface ShoppingCartDao extends AbstractDao<ShoppingCart> {
    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
