package com.cinema.dao;

import com.cinema.model.Order;
import com.cinema.model.User;
import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> getOrdersHistory(User user);
}
