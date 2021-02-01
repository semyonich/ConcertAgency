package com.cinema;

import com.cinema.lib.Injector;
import com.cinema.model.CinemaHall;
import com.cinema.model.Movie;
import com.cinema.model.MovieSession;
import com.cinema.service.CinemaHallService;
import com.cinema.service.MovieService;
import com.cinema.service.MovieSessionService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Application {
    private static Injector injector = Injector.getInstance("com.cinema");

    public static void main(String[] args) {
        // Movies
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
//        System.out.println(movieService.getAll());
        Movie movie1 = new Movie();
        movie1.setTitle("Empire strikes back");
        movie1.setDescription("Long Long ago in a distant galaxy...");
        movieService.add(movie1);
        Movie movie2 = new Movie();
        movie2.setTitle("DeadPool-2");
        movie2.setDescription("Spiderman like");
        movieService.add(movie2);
//        System.out.println(movieService.getAll());
        //Cinemahalls
        CinemaHallService cinemaHallService = (CinemaHallService) injector.getInstance(CinemaHallService.class);
//        System.out.println(cinemaHallService.getAll());
        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setDescription("cinema hall 1");
        cinemaHall1.setCapacity(100);
        cinemaHallService.add(cinemaHall1);
        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setDescription("cinema hall 2");
        cinemaHall2.setCapacity(200);
        cinemaHallService.add(cinemaHall2);
        System.out.println(cinemaHallService.getAll());
        //MovieSessions
        MovieSessionService movieSessionService = (MovieSessionService) injector
                .getInstance(MovieSessionService.class);
        System.out.println(movieSessionService.getAll());
        MovieSession movieSession1 = new MovieSession();
        MovieSession movieSession2 = new MovieSession();
        MovieSession movieSession3 = new MovieSession();
        MovieSession movieSession4 = new MovieSession();
        LocalDateTime movie1Time = LocalDateTime.now();
        LocalDateTime movie2Time = LocalDateTime.now().plusHours(3);
        LocalDateTime movie3Time = LocalDateTime.now().plusDays(3);

        movieSession1.setShowTime(movie1Time);
        movieSession1.setMovie(movie1);
        movieSession1.setCinemaHall(cinemaHall1);
        movieSessionService.add(movieSession1);

        movieSession2.setShowTime(movie2Time);
        movieSession2.setMovie(movie2);
        movieSession2.setCinemaHall(cinemaHall1);
        movieSessionService.add(movieSession2);

        movieSession3.setShowTime(movie3Time);
        movieSession3.setMovie(movie1);
        movieSession3.setCinemaHall(cinemaHall1);
        movieSessionService.add(movieSession3);

        movieSession4.setShowTime(movie2Time);
        movieSession4.setMovie(movie1);
        movieSession3.setCinemaHall(cinemaHall2);
        movieSessionService.add(movieSession4);
        System.out.println("Available movie sessions are: \n"
                + movieSessionService.findAvailableSessions(1L, LocalDate.now()));
        System.out.println(movieSessionService.getAll());

    }
}
