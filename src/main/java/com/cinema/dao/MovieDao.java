package com.cinema.dao;

import com.cinema.model.Movie;
import java.util.List;

public interface MovieDao extends GenericDao<Movie> {
    List<Movie> getAll();
}
