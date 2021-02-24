package com.concert.agency.dao;

import com.concert.agency.model.Order;
import com.concert.agency.model.User;
import java.util.List;

public interface OrderDao extends AbstractDao<Order> {
    List<Order> getOrdersHistory(User user);
}
