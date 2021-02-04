package com.cinema;

import com.cinema.lib.Injector;
import com.cinema.model.CinemaHall;
import com.cinema.model.Movie;
import com.cinema.model.MovieSession;
import com.cinema.model.ShoppingCart;
import com.cinema.model.User;
import com.cinema.security.AuthenticationService;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import com.cinema.service.MovieSessionService;
import com.cinema.service.ShoppingCartService;
import java.time.LocalDateTime;

public class Application {
    private static Injector injector = Injector.getInstance("com.cinema");

    public static void main(String[] args) {
        System.out.println("=============Movies tests==============");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie movie1 = new Movie();
        movie1.setTitle("Empire strikes back");
        movie1.setDescription("Long Long ago in a distant galaxy...");
        movieService.add(movie1);
        Movie movie2 = new Movie();
        movie2.setTitle("DeadPool-2");
        movie2.setDescription("Spiderman like");
        movieService.add(movie2);
        System.out.println("=============CinemaHalls tests==============");
        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setDescription("ch1");
        cinemaHall1.setCapacity(100);
        CinemaHallService cinemaHallService = (CinemaHallService) injector
                .getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall1);
        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setDescription("ch2");
        cinemaHall2.setCapacity(200);
        cinemaHallService.add(cinemaHall2);
        System.out.println(cinemaHallService.getAll());
        System.out.println("=============MovieSessions tests==============");
        MovieSession movieSession1 = new MovieSession();
        LocalDateTime movie1Time = LocalDateTime.now();
        movieSession1.setShowTime(movie1Time);
        movieSession1.setMovie(movie1);
        movieSession1.setCinemaHall(cinemaHall1);
        MovieSessionService movieSessionService = (MovieSessionService) injector
                .getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession1);
        MovieSession movieSession2 = new MovieSession();
        LocalDateTime movie2Time = LocalDateTime.now().plusHours(3);
        movieSession2.setShowTime(movie2Time);
        movieSession2.setMovie(movie2);
        movieSession2.setCinemaHall(cinemaHall1);
        movieSessionService.add(movieSession2);
        System.out.println("=============Users tests==============");
        AuthenticationService authService = (AuthenticationService) injector
                .getInstance(AuthenticationService.class);
        User user1 = authService.register("kkk@gmail.com", "123456");
        User user2 = authService.register("serhii@gmail.com", "123456");
        System.out.println("=============ShoppingCart & Tickets tests==============");
        ShoppingCartService shoppingCartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession1, user1);
        shoppingCartService.addSession(movieSession2, user2);
        shoppingCartService.addSession(movieSession2, user1);
        ShoppingCart user1ShoppingCart = shoppingCartService.getByUser(user1);
        shoppingCartService.clear(user1ShoppingCart);
    }
}
