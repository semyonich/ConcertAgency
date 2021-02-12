package com.cinema.dao;

import com.cinema.model.Movie;
import java.util.List;

public interface MovieDao extends AbstractDao<Movie> {
    List<Movie> getAll();

    Movie getById(Long id);
}
