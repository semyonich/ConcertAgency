package com.cinema.dao;

import com.cinema.model.Order;
import com.cinema.model.User;
import java.util.List;

public interface OrderDao extends AbstractDao<Order> {
    List<Order> getOrdersHistory(User user);
}
