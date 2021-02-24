package com.concert.agency.service;

import com.concert.agency.model.ConcertHall;
import java.util.List;

public interface ConcertHallService {
    ConcertHall add(ConcertHall concertHall);

    List<ConcertHall> getAll();

    ConcertHall getById(Long id);
}
