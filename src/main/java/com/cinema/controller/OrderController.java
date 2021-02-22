package com.cinema.controller;

import com.cinema.exception.DataProcessingException;
import com.cinema.model.ShoppingCart;
import com.cinema.model.User;
import com.cinema.model.dto.OrderResponseDto;
import com.cinema.service.OrderService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import com.cinema.service.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public OrderController(OrderService orderService, OrderMapper orderMapper,
                           ShoppingCartService shoppingCartService, UserService userService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @PostMapping("/complete")
    public void completeOrder(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email).get();
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        if (shoppingCart.getTickets().isEmpty()) {
            throw new DataProcessingException("Your cart is empty");
        }
        orderService.completeOrder(shoppingCart);
    }

    @GetMapping
    public List<OrderResponseDto> getAllOrdersByUserId(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email).get();
        return orderService.getOrdersHistory(user).stream()
                .map(orderMapper::makeDto)
                .collect(Collectors.toList());
    }
}
