package com.cinema.dao;

import com.cinema.model.CinemaHall;
import java.util.List;

public interface CinemaHallDao extends AbstractDao<CinemaHall> {
    List<CinemaHall> getAll();

    CinemaHall getById(Long id);
}
