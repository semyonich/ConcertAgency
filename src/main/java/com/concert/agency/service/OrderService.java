package com.concert.agency.service;

import com.concert.agency.model.Order;
import com.concert.agency.model.ShoppingCart;
import com.concert.agency.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
