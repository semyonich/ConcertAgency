package com.cinema.dao;

import com.cinema.model.Order;
import com.cinema.model.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrdersHistory(User user);
}
