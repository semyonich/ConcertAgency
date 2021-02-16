package com.cinema.dao;

import com.cinema.model.ShoppingCart;
import com.cinema.model.User;
import java.util.Optional;

public interface ShoppingCartDao extends AbstractDao<ShoppingCart> {
    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);

    Optional<ShoppingCart> findById(Long id);
}
