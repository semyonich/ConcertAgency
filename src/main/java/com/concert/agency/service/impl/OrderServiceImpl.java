package com.concert.agency.service.impl;

import com.concert.agency.dao.OrderDao;
import com.concert.agency.model.Order;
import com.concert.agency.model.ShoppingCart;
import com.concert.agency.model.Ticket;
import com.concert.agency.model.User;
import com.concert.agency.service.OrderService;
import com.concert.agency.service.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final ShoppingCartService shoppingCartService;

    public OrderServiceImpl(OrderDao orderDao, ShoppingCartService shoppingCartService) {
        this.orderDao = orderDao;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        List<Ticket> tickets = shoppingCart.getTickets();
        User user = shoppingCart.getUser();
        Order order = new Order();
        order.setUser(user);
        order.setTickets(tickets);
        order.setOrderDate(LocalDateTime.now());
        orderDao.add(order);
        shoppingCart.setTickets(new ArrayList<>());
        shoppingCartService.clear(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        return orderDao.getOrdersHistory(user);
    }
}
