package com.cinema;

import com.cinema.lib.Injector;
import com.cinema.model.Movie;
import com.cinema.service.MovieService;

public class Application {
    private static Injector injector = Injector.getInstance("com.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        System.out.println(movieService.getAll());
        Movie movie1 = new Movie();
        movie1.setTitle("Empire strikes back");
        movie1.setDescription("Long Long ago in a distant galaxy...");
        movieService.add(movie1);
        Movie movie2 = new Movie();
        movie2.setTitle("DeadPool-2");
        movie2.setDescription("Spiderman like");
        movieService.add(movie2);
        System.out.println(movieService.getAll());
    }
}
