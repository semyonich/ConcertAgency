package com.concert.agency.service.impl;

import com.concert.agency.dao.ConcertHallDao;
import com.concert.agency.model.ConcertHall;
import com.concert.agency.service.ConcertHallService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ConcertHallServiceImpl implements ConcertHallService {
    private final ConcertHallDao concertHallDao;

    public ConcertHallServiceImpl(ConcertHallDao concertHallDao) {
        this.concertHallDao = concertHallDao;
    }

    @Override
    public ConcertHall add(ConcertHall cinemaHall) {
        return concertHallDao.add(cinemaHall);
    }

    @Override
    public List<ConcertHall> getAll() {
        return concertHallDao.getAll();
    }

    @Override
    public ConcertHall getById(Long id) {
        return concertHallDao.getById(id);
    }
}
