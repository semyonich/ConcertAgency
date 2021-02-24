package com.concert.agency.service.impl;

import com.concert.agency.dao.PerformanceDao;
import com.concert.agency.model.Performance;
import com.concert.agency.service.PerformanceService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PerformanceServiceImpl implements PerformanceService {
    private final PerformanceDao performanceDao;

    public PerformanceServiceImpl(PerformanceDao performanceDao) {
        this.performanceDao = performanceDao;
    }

    @Override
    public Performance add(Performance performance) {
        return performanceDao.add(performance);
    }

    @Override
    public List<Performance> getAll() {
        return performanceDao.getAll();
    }

    @Override
    public Performance getById(Long id) {
        return performanceDao.getById(id);
    }
}
