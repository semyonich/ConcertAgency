package com.cinema.controller;

import com.cinema.exception.DataProcessingException;
import com.cinema.model.MovieSession;
import com.cinema.model.ShoppingCart;
import com.cinema.model.User;
import com.cinema.model.dto.ShoppingCartResponseDto;
import com.cinema.service.MovieSessionService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import com.cinema.service.mapper.ShoppingCartMapper;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final MovieSessionService movieSessionService;
    private final ShoppingCartMapper mapper;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionService movieSessionService,
                                  ShoppingCartMapper mapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
        this.mapper = mapper;
    }

    @PostMapping("/movie-sessions")
    public void AddMovieSession(@RequestParam Long userId, @RequestParam Long movieSessionId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new DataProcessingException("User don't exist, id=" + userId));
        MovieSession movieSession = movieSessionService.findById(movieSessionId)
                .orElseThrow(() -> new DataProcessingException("MovieSession don't exist, id="
                        + movieSessionId));
        shoppingCartService.addSession(movieSession, user);
    }

    //Get by user - GET: /shopping-carts/by-user?userId
    @GetMapping("/by-user")
    ShoppingCartResponseDto getUserCart(@RequestParam Long userId) {
        ShoppingCart userCart = shoppingCartService.findById(userId)
                .orElseThrow(() -> new DataProcessingException("ShoppingCart don't exist"));
        return mapper.makeDto(userCart);
    }
}
