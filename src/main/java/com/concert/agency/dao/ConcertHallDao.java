package com.concert.agency.dao;

import com.concert.agency.model.ConcertHall;
import java.util.List;

public interface ConcertHallDao extends AbstractDao<ConcertHall> {
    List<ConcertHall> getAll();

    ConcertHall getById(Long id);
}
