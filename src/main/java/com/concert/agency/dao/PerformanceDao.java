package com.concert.agency.dao;

import com.concert.agency.model.Performance;
import java.util.List;

public interface PerformanceDao extends AbstractDao<Performance> {
    List<Performance> getAll();

    Performance getById(Long id);
}
