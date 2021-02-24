package com.concert.agency.controller;

import com.concert.agency.model.Event;
import com.concert.agency.model.ShoppingCart;
import com.concert.agency.model.User;
import com.concert.agency.model.dto.ShoppingCartResponseDto;
import com.concert.agency.service.EventService;
import com.concert.agency.service.ShoppingCartService;
import com.concert.agency.service.UserService;
import com.concert.agency.service.mapper.ShoppingCartMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final EventService eventService;
    private final ShoppingCartMapper mapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  EventService eventService,
                                  ShoppingCartMapper mapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.eventService = eventService;
        this.mapper = mapper;
    }

    @PostMapping("/events")
    public void addEvent(Authentication authentication, @RequestParam Long eventId) {
        String email = authentication.getName();
        User user = userService.findByEmail(email).get();
        Event event = eventService.get(eventId);
        shoppingCartService.addSession(event, user);
    }

    @GetMapping("/by-user")
    ShoppingCartResponseDto getUserCart(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email).get();
        ShoppingCart userCart = shoppingCartService.getByUser(user);
        return mapper.makeDto(userCart);
    }
}
