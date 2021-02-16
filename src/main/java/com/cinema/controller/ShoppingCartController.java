package com.cinema.controller;

import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/*Add movie session - POST: /shopping-carts/movie-sessions?userId&movieSessionId
Get by user - GET: /shopping-carts/by-user?userId*/
@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @PostMapping("/movie-sessions")
    public void AddMovieSession(@RequestParam Long userId, @RequestParam Long movieSessionId) {

    }
}
